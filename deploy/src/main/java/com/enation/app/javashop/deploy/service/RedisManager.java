package com.enation.app.javashop.deploy.service;

import com.enation.app.javashop.deploy.model.Redis;
import com.enation.app.javashop.framework.database.WebPage;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * redis业务层
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2018-05-04 20:04:36
 */
public interface RedisManager	{

	/**
	 * 查询redis列表
	 * @param page 页码
	 * @param pageSize 每页数量
	 * @return WebPage
	 */
	WebPage list(long page, long pageSize);
	/**
	 * 添加redis
	 * @param redis redis
	 * @return Redis redis
	 */
	Redis add(Redis redis);

	/**
	 * 修改redis
	 * @param redis redis
	 * @param id redis主键
	 * @return Redis redis
	 */
	Redis edit(Redis redis, Long id);

	/**
	 * 删除redis
	 * @param id redis主键
	 */
	void delete(Long id);

	/**
	 * 获取redis
	 * @param id redis主键
	 * @return Redis  redis
	 */
	Redis getModel(Long id);


	/**
	 * 根据depolyid获取 Redis配置信息
	 * @param deployId 部署id
	 * @return redis配置信息
	 */
	Redis getByDeployId(Long deployId);


	/**
	 * 测试redis连接
	 * @param redis  reids信息
	 * @return 是否连接成功
	 */
	boolean testConnection(Redis redis);


	/**
	 * 构建redis  Connection
	 * @param redis
	 * @return
	 */
	StringRedisTemplate getConnection(Redis redis);

	/**
	 * 初始化redis
	 * @param deployId
	 */
	void initRedis(Integer deployId);
}
