package com.enation.app.javashop.deploy.api;

import com.enation.app.javashop.deploy.model.Database;
import com.enation.app.javashop.deploy.model.Deploy;
import com.enation.app.javashop.deploy.service.DatabaseManager;
import com.enation.app.javashop.deploy.service.DeployManager;
import com.enation.app.javashop.framework.database.WebPage;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 部署数据控制器
 * Created by kingapex on 2018/4/23.
 *
 * @author kingapex
 * @version 1.0
 * @since 7.0.0
 * 2018/4/23
 */

@RestController
@RequestMapping("/data/deploys")
public class DeployDataController {


    @Autowired
    private DeployManager deployManager;

    @Autowired
    private DatabaseManager databaseManager;

    @ApiOperation(value = "查询部署列表", response = Deploy.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page_no", value = "页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "page_size", value = "每页显示数量", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping
    public WebPage list(@ApiIgnore Long pageNo, @ApiIgnore Long pageSize) {

        return this.deployManager.list(pageNo, pageSize);
    }


    @ApiOperation(value = "添加部署", response = Deploy.class)
    @PostMapping
    public Deploy add(@Valid Deploy deploy) {

        this.deployManager.add(deploy);

        return deploy;
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "修改部署", response = Deploy.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = true, dataType = "int", paramType = "path")
    })
    public Deploy edit(@Valid Deploy deploy, @PathVariable Long id) {
        this.deployManager.edit(deploy, id);
        return new Deploy();
    }


    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除部署")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要删除的部署主键", required = true, dataType = "int", paramType = "path")
    })
    public String delete(@PathVariable Long id) {

        this.deployManager.delete(id);

        return "";
    }


    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询一个部署")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要查询的部署主键", required = true, dataType = "int", paramType = "path")
    })
    public Deploy get(@PathVariable Long id) {

        Deploy deploy = this.deployManager.getModel(id);

        return deploy;
    }

    @GetMapping(value = "/{id}/databases")
    @ApiOperation(value = "获取一个部署的数据库列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "要查询的部署主键", required = true, dataType = "int", paramType = "path")
    })
    public List<Database> getDatabases(@PathVariable Long id) {
        List dbList = databaseManager.list(id);
        return dbList;
    }

}
