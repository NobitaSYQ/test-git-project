package com.enation.app.javashop.deploy.service.impl;

import com.enation.app.javashop.deploy.model.Redis;
import com.enation.app.javashop.deploy.service.DeployExecutor;
import com.enation.app.javashop.deploy.service.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @description: redis执行器
 * @author: liuyulei
 * @create: 2020-03-26 09:30
 * @version:1.0
 * @since:7.2.0
 **/
@Service
public class RedisDeployExecutor implements DeployExecutor {

    @Autowired
    private RedisManager redisManager;


    @Override
    public void deploy(Long deployId) {
        Redis redis =  this.redisManager.getByDeployId(deployId);
        StringRedisTemplate redisTemplate =  redisManager.getConnection(redis);
        Set keys = redisTemplate.keys("*");
        redisTemplate.delete(keys);

    }

    @Override
    public String getType() {
        return "redis";
    }
}
