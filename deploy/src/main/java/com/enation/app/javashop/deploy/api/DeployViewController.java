package com.enation.app.javashop.deploy.api;

import com.enation.app.javashop.deploy.service.DatabaseManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by kingapex on 2018/4/20.
 *
 * @author kingapex
 * @version 1.0
 * @since 7.0.0
 * 2018/4/20
 */
@Controller
@RequestMapping("/view/deploy")
public class DeployViewController {

    @Autowired
    private DatabaseManager databaseManager;

    @RequestMapping("/list")
    public String index(){

        return "deploy_list";
    }

    @RequestMapping("/{deploy_id}/databases")
    public String database(@PathVariable("deploy_id") Long deployId, Model model){
        List dbList  =  databaseManager.list(deployId);
        model.addAttribute("dbList", dbList);
        return "database";
    }
}
