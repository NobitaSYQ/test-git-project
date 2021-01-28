package com.enation.app.javashop.deploy.service;

import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.deploy.model.Rabbitmq;

/**
 * rabbitmq业务层
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2018-05-04 20:31:41
 */
public interface RabbitmqManager	{

	/**
	 * 查询rabbitmq列表
	 * @param page 页码
	 * @param pageSize 每页数量
	 * @return WebPage
	 */
	WebPage list(long page, long pageSize);
	/**
	 * 添加rabbitmq
	 * @param rabbitmq rabbitmq
	 * @return Rabbitmq rabbitmq
	 */
	Rabbitmq add(Rabbitmq rabbitmq);

	/**
	* 修改rabbitmq
	* @param rabbitmq rabbitmq
	* @param id rabbitmq主键
	* @return Rabbitmq rabbitmq
	*/
	Rabbitmq edit(Rabbitmq rabbitmq, Long id);

	/**
	 * 删除rabbitmq
	 * @param id rabbitmq主键
	 */
	void delete(Long id);

	/**
	 * 获取rabbitmq
	 * @param id rabbitmq主键
	 * @return Rabbitmq  rabbitmq
	 */
	Rabbitmq getModel(Long id);


	/**
	 * 根据部署 id 获取rabbitmq
	 * @param deployId 部署id
	 * @return
	 */
	Rabbitmq getByDeployId(Integer deployId);



	/**
	 * 初始化rabbitmq信息
	 * @param delpoyId 部署id
	 */
	void initRabbitMq(Integer delpoyId);


	/**
	 * 测试rabbitmq
	 * @param rabbitmq  rabbitmq配置信息
	 * @return 是否连接成功
	 */
	boolean testConnection(Rabbitmq rabbitmq);
}
