package com.enation.app.javashop.deploy.service;

import com.enation.app.javashop.deploy.model.Deploy;
import com.enation.app.javashop.framework.database.WebPage;

/**
 * 部署业务层
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2018-04-23 14:27:13
 */
public interface DeployManager	{

	/**
	 * 查询部署列表
	 * @param page 页码
	 * @param pageSize 每页数量
	 * @return WebPage
	 */
	WebPage list(long page, long pageSize);
	/**
	 * 添加部署
	 * @param deploy 部署
	 * @return Deploy 部署
	 */
	Deploy add(Deploy deploy);

	/**
	* 修改部署
	* @param deploy 部署
	* @param id 部署主键
	* @return Deploy 部署
	*/
	Deploy edit(Deploy deploy, Long id);

	/**
	 * 删除部署
	 * @param id 部署主键
	 */
	void delete(Long id);

	/**
	 * 获取部署
	 * @param id 部署主键
	 * @return Deploy  部署
	 */
	Deploy getModel(Long id);



}
