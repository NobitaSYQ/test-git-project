package com.enation.app.javashop.deploy.service.impl;

import com.enation.app.javashop.deploy.model.Deploy;
import com.enation.app.javashop.deploy.service.*;
import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.framework.util.DateUtil;
import com.enation.app.javashop.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.javashop.framework.database.DaoSupport;


/**
 * 部署业务类
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2018-04-23 14:27:13
 */
@Service
public class DeployManagerImpl implements DeployManager {

	@Autowired
	private	DaoSupport	daoSupport;

	@Autowired
	private DatabaseManager databaseManager;

	@Autowired
	private RedisManager redisManager;

	@Autowired
	private RabbitmqManager rabbitmqManager;

	@Autowired
	private ElasticsearchManager elasticsearchManager;

	@Override
	public WebPage list(long page, long pageSize){

		String sql = "select * from es_deploy order by deploy_id desc ";
		WebPage webPage = this.daoSupport.queryForPage(sql,page, pageSize ,Deploy.class );

		return webPage;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public	Deploy  add(Deploy	deploy)	{

		deploy.setCreateTime(DateUtil.getDateline());
		this.daoSupport.insert(deploy);
		Integer deployId = this.daoSupport.queryForInt("select max(deploy_id) from es_deploy");

		//初始化数据库设置
		databaseManager.initDatabase(deployId);

		//初始化elasticsearch
		elasticsearchManager.initElasticsearch(StringUtil.toLong(deployId.toString(),0));

		deploy.setDeployId(deployId);
		return deploy;
	}



	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public	Deploy  edit(Deploy	deploy,Long id){
		this.daoSupport.update(deploy, id);
		return deploy;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public	void delete( Long id)	{
		this.daoSupport.delete(Deploy.class,	id);
	}

	@Override
	public	Deploy getModel(Long id)	{
		return this.daoSupport.queryForObject(Deploy.class, id);
	}




}
