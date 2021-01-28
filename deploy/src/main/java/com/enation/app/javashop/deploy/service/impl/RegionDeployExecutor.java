package com.enation.app.javashop.deploy.service.impl;

import com.enation.app.javashop.deploy.service.DeployExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kingapex on 2018-12-28.
 * 地区数据部署
 * @author kingapex
 * @version 1.0
 * @since 7.0.0
 * 2018-12-28
 */
@Service
public class RegionDeployExecutor implements DeployExecutor {

    @Autowired
    private  DataBaseDeployExecutor dataBaseDeployExecutor;

    @Override
    public void deploy(Long deployId) {

        dataBaseDeployExecutor.importRegionSQl(deployId);

    }

    @Override
    public String getType() {
        return "region";
    }
}
