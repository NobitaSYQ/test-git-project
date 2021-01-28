package com.enation.app.javashop.deploy.service.impl;

import com.enation.app.javashop.deploy.model.Rabbitmq;
import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.deploy.util.ObjectMapperUtil;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.javashop.framework.database.DaoSupport;
import com.enation.app.javashop.deploy.service.RabbitmqManager;

import java.util.Map;

/**
 * rabbitmq业务类
 *
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2018-05-04 20:31:41
 */
@Service
public class RabbitmqManagerImpl implements RabbitmqManager {

    @Autowired
    private DaoSupport daoSupport;

    @Override
    public WebPage list(long page, long pageSize) {

        String sql = "select * from es_rabbitmq  ";
        WebPage webPage = this.daoSupport.queryForPage(sql, page, pageSize, Rabbitmq.class);

        return webPage;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Rabbitmq add(Rabbitmq rabbitmq) {
        Map map = ObjectMapperUtil.toMap(rabbitmq);
        map.remove("id");
        this.daoSupport.insert("es_rabbitmq", map);

        return rabbitmq;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Rabbitmq edit(Rabbitmq rabbitmq, Long id) {
        this.daoSupport.update(rabbitmq, id);
        return rabbitmq;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Long id) {
        this.daoSupport.delete(Rabbitmq.class, id);
    }

    @Override
    public Rabbitmq getModel(Long id) {

        return this.daoSupport.queryForObject(Rabbitmq.class, id);
    }

    @Override
    public Rabbitmq getByDeployId(Integer deployId) {

        String sql = "select * from es_rabbitmq  where deploy_id=?";

        return daoSupport.queryForObject(sql, Rabbitmq.class, deployId);
    }


    @Override
    public void initRabbitMq(Integer delpoyId) {
        Rabbitmq rabbitmq = new Rabbitmq();
        rabbitmq.setHost("127.0.0.1");
        rabbitmq.setPort("5672");
        rabbitmq.setUsername("guest");
        rabbitmq.setPassword("guest");
        rabbitmq.setHost("/");
        rabbitmq.setDeployId(delpoyId);
        this.add(rabbitmq);
    }

    @Override
    public boolean testConnection(Rabbitmq rabbitmq) {
        try {
            CachingConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitmq.getHost(), Integer.valueOf(rabbitmq.getPort()));
            connectionFactory.setUsername(rabbitmq.getUsername());
            connectionFactory.setPassword(rabbitmq.getPassword());
            connectionFactory.setConnectionTimeout(30000);
            connectionFactory.createConnection();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
