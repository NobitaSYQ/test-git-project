package com.enation.app.javashop.deploy.service;

import com.enation.app.javashop.deploy.model.Elasticsearch;
import com.enation.app.javashop.framework.database.WebPage;

/**
 * elasticsearch业务层
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2019-02-13 10:39:25
 */
public interface ElasticsearchManager	{

	/**
	 * 查询elasticsearch列表
	 * @param page 页码
	 * @param pageSize 每页数量
	 * @return WebPage
	 */
	WebPage list(long page, long pageSize);
	/**
	 * 添加elasticsearch
	 * @param elasticsearch elasticsearch
	 * @return Elasticsearch elasticsearch
	 */
	Elasticsearch add(Elasticsearch elasticsearch);

	/**
	* 修改elasticsearch
	* @param elasticsearch elasticsearch
	* @param id elasticsearch主键
	* @return Elasticsearch elasticsearch
	*/
	Elasticsearch edit(Elasticsearch elasticsearch, Long id);

	/**
	 * 删除elasticsearch
	 * @param id elasticsearch主键
	 */
	void delete(Long id);

	/**
	 * 获取elasticsearch
	 * @param id elasticsearch主键
	 * @return Elasticsearch  elasticsearch
	 */
	Elasticsearch getModel(Long id);


	/**
	 * 根据部署 id 获取 Elasticsearch
	 * @param deployId 部署id
	 * @return Elasticsearch部署信息
	 */
	Elasticsearch getByDeployId(Long deployId);


	/**
	 * 初始化elasticsearch
	 * @param deployId 部署id
	 */
	void initElasticsearch(Long deployId);

	/**
	 * 测试elasticsearch连接
	 * @param elasticsearch 连接信息
	 * @return 测试结果
	 */
	boolean testConnection(Elasticsearch elasticsearch);



}
