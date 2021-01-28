package com.enation.app.javashop.deploy.service.impl;

import com.enation.app.javashop.deploy.enums.ServiceType;
import com.enation.app.javashop.deploy.model.Database;
import com.enation.app.javashop.deploy.model.Deploy;
import com.enation.app.javashop.deploy.service.DatabaseManager;
import com.enation.app.javashop.deploy.service.DeployExecutor;
import com.enation.app.javashop.deploy.service.DeployManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kingapex on 2018/5/14.
 * 数据库部署执行器实现
 * @author kingapex
 * @version 1.0
 * @since 7.0.0
 * 2018/5/14
 */
@Service
public class DataBaseDeployExecutor implements DeployExecutor {


    @Autowired
    private DatabaseManager databaseManager;

    @Autowired
    private DeployManager deployManager;


    @Override
    public void deploy(Long deployId) {

        Deploy deploy = this.deployManager.getModel(deployId);

        //类型名即是目录名
        String deployType = deploy.getDeployType();

        List<Database> dbList = databaseManager.list(deployId);
        for (Database database : dbList) {
            importSql(deployEnum(deployType), database);
        }
    }

    @Override
    public String getType() {
        return "database";
    }

    private String deployEnum(String deployType) {
        if(deployType.equals("无示例数据")){
            return "basic";
        }else if (deployType.equals("带示例数据")) {
            return "standard";
        } else{
            return deployType;
        }
    }

    public void importRegionSQl(Long deployId) {
        Database database = getSystemDataBase(deployId);
        String relativePath = "scheme/regions.sql";
        JdbcTemplate jdbcTemplate = databaseManager.createJdbcTemplate(database);
        databaseManager.executeSql(relativePath,jdbcTemplate);
    }


    private Database getSystemDataBase(Long deployId) {
        List<Database> dbList = databaseManager.list(deployId);
        for (Database database : dbList) {
            String serviceType = database.getServiceType();

            //找到系统服务
            if (ServiceType.DEFAULT_DATABASE.name().equals(serviceType)) {
                return database;
            }
        }
        throw  new RuntimeException("未找到系统库");
    }

    /**
     * 导入一个数据库的sql文件
     * 这个sql文件会在部署类型下
     * 以database的ServiceType 文件名
     *
     * @param deployType 部署类型
     * @param database   数据库
     */
    private void importSql(String deployType, Database database) {

        String serviceType = database.getServiceType().toLowerCase();
        String relativePath = "scheme/" + deployType + "/" + serviceType + ".sql";
        JdbcTemplate jdbcTemplate = databaseManager.createJdbcTemplate(database);
        /**
         * 安装SQL之前，首先删除库中所有表
         *  这么做的目的是防止旧版数据库中的表，在新版中不存在，直接执行安装sql，旧版无用表还会存在
         */
        List<String> dropSql = databaseManager.dropTableSql(serviceType,database);
        databaseManager.dropTable(dropSql,jdbcTemplate);
        databaseManager.executeSql(relativePath,jdbcTemplate);

    }

}
