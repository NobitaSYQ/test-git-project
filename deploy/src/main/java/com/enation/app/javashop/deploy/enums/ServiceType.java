package com.enation.app.javashop.deploy.enums;

/**
 *
 * Javashop业务类型
 * @author kingapex
 * @version 1.0
 * @since 7.0.0
 * 2018/4/24
 */
public enum ServiceType {

    /**
     * 默认数据库
     */
    DEFAULT_DATABASE("default_database"),


    /**
     * xxl-job
     */
    XXL_JOB("xxl-job");

    /**
     * 类型名称
     */
    private String typeName;


    ServiceType(String typeName){
        this.typeName = typeName;
    }


    /**
     * 获取类型名称
     * @return
     */
    public String getTypeName(){
        return  this.typeName;
    }

}
