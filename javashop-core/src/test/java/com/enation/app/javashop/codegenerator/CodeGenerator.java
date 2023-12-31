package com.enation.app.javashop.codegenerator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;

/**
 * Mybatis代码生成
 *
 * @author lewis
 * @data 2020/9/8.
 */
public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();

        // 项目路径
        String projectPath = "D:/mybatisplus-codegenerator/bmtp/";
        gc.setOutputDir(projectPath + "src/main/java");
        gc.setAuthor("lewis");
        // 是否打开输出目录
        gc.setOpen(false);

        // 是否覆盖已生成的文件
        gc.setFileOverride(false);

        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);

        // tableId类型
        gc.setIdType(IdType.ASSIGN_ID);

        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/javashop?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setParent("com.enation.app.javashop");
        pc.setModuleName("");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "src/main/resources/mapper/"
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);

        // 公共父类实体,没有就不用设置
//        strategy.setSuperEntityClass("com.enation.app.javashop.model.base.BaseEntity");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("create_time", "create_by", "update_time", "update_by", "delete_status");

        // 实体是否使用Lombok
        strategy.setEntityLombokModel(false);

        // 控制器是否是Rest
        strategy.setRestControllerStyle(true);
        // 公共父类控制器,没有就不用设置
        strategy.setSuperControllerClass("");

        // 表名，多个英文逗号分割
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude("es_member_group_operator_record", "es_member_credit","es_member_whitelist");

        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(false);

        // 表前缀 如 "t_"
        strategy.setTablePrefix("t_", "es_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
