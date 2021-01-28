package com.enation.app.javashop.deploy.service;

/**
 * 部署执行接口
 * @author kingapex
 * @version 1.0
 * @since 7.0.0
 * 2018/5/14
 */
public interface DeployExecutor {


   /**
    * 执行部署
    * @param deployId 部署id
    */
   void deploy(Long deployId);

   /**
    * 定义类型
    * @return
    */
   String getType();

}
