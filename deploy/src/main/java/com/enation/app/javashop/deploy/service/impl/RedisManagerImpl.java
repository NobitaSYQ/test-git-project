package com.enation.app.javashop.deploy.service.impl;

import com.enation.app.javashop.deploy.model.Redis;
import com.enation.app.javashop.deploy.service.RedisManager;
import com.enation.app.javashop.deploy.util.ObjectMapperUtil;
import com.enation.app.javashop.framework.database.DaoSupport;
import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.framework.exception.ServiceException;
import com.enation.app.javashop.framework.exception.SystemErrorCodeV1;
import com.enation.app.javashop.framework.logs.Logger;
import com.enation.app.javashop.framework.logs.LoggerFactory;
import com.enation.app.javashop.framework.redis.builder.StringRedisTemplateBuilder;
import com.enation.app.javashop.framework.redis.configure.*;
import com.enation.app.javashop.framework.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * redis业务类
 *
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2018-05-04 20:04:36
 */
@Service
public class RedisManagerImpl implements RedisManager {

    @Autowired
    private DaoSupport daoSupport;

    @Autowired
    private List<IRedisBuilder> redisBuilder;

    private static Logger logger = LoggerFactory.getLogger(StringRedisTemplateBuilder.class);

    /**
     * 构建锁
     */
    private static final Lock LOCK = new ReentrantLock();


    @Override
    public WebPage list(long page, long pageSize) {

        String sql = "select * from es_redis  ";
        WebPage webPage = this.daoSupport.queryForPage(sql, page, pageSize, Redis.class);

        return webPage;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Redis add(Redis redis) {
        Map map = ObjectMapperUtil.toMap(redis);
        map.remove("redis_id");
        this.daoSupport.insert("es_redis", map);

        return redis;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Redis edit(Redis redis, Long id) {
        this.daoSupport.update(redis, id);
        return redis;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Long id) {
        this.daoSupport.delete(Redis.class, id);
    }

    @Override
    public Redis getModel(Long id) {
        return this.daoSupport.queryForObject(Redis.class, id);
    }

    @Override
    public Redis getByDeployId(Long deployId) {
        String sql = "select * from es_redis where deploy_id=?";
        Redis redis = daoSupport.queryForObject(sql, Redis.class, deployId);
        return redis;
    }

    @Override
    public boolean testConnection(Redis redis) {
        try {

            StringRedisTemplate template = this.getConnection(redis);
            String test = "test";
            template.opsForValue().set(test, test);
            String result = template.opsForValue().get(test);
            return test.equals(result);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public StringRedisTemplate getConnection(Redis redis) {

        RedisConnectionConfig config = getRedisConnectionConfig(redis);

        StringRedisTemplate redisTemplate = null;

        JedisSetting.loadPoolConfig(config);

        while (true) {
            try {
                LOCK.tryLock(10, TimeUnit.MILLISECONDS);
                if (redisTemplate == null) {

                    IRedisBuilder redisBuilder = this.getRedisBuilder(config.getType());
                    LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisBuilder.buildConnectionFactory(config);
                    // 初始化连接pool
                    lettuceConnectionFactory.afterPropertiesSet();
                    redisTemplate = new StringRedisTemplate();
                    redisTemplate.setConnectionFactory(lettuceConnectionFactory);
                    redisTemplate.afterPropertiesSet();
                    return redisTemplate;
                }
            } catch (Exception e) {
                // 容错
                logger.error(e.getMessage(), e);
                break;
            } finally {
                LOCK.unlock();
            }
            try {
                // 活锁
                TimeUnit.MILLISECONDS.sleep(200 + new Random().nextInt(1000));
            } catch (InterruptedException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return redisTemplate;
    }

    @Override
    public void initRedis(Integer deployId) {
        Redis redis = new Redis();
        redis.setDeployId(deployId);
        redis.setStandaloneHost("localhost");
        redis.setStandalonePort("6379");
        redis.setConfigType("manual");
        redis.setRedisType("standalone");
        add(redis);

    }

    private IRedisBuilder getRedisBuilder(String redisType) {

        for (IRedisBuilder builder : redisBuilder) {
            if (builder.getType().name().equals(redisType)) {
                return builder;
            }
        }
        throw new ServiceException(SystemErrorCodeV1.INVALID_CONFIG_PARAMETER, "错误的redis 配置类型，请检查");
    }

    private RedisConnectionConfig getRedisConnectionConfig(Redis redis) {
        RedisConnectionConfig config = new RedisConnectionConfig();
        config.setType(redis.getRedisType());
        config.setConfigType(redis.getConfigType());
        config.setClusterNodes(redis.getClusterNodes());

        config.setHost(redis.getStandaloneHost());
        config.setPassword(redis.getStandalonePassword());
        config.setPort(Integer.valueOf(redis.getStandalonePort()));
        config.setRestAppid(redis.getRestAppid());
        config.setRestUrl(redis.getRestUrl());
        config.setSentinelMaster(redis.getSentinelMaster());
        config.setSentinelNodes(redis.getSentinelNodes());
        return config;
    }

}
