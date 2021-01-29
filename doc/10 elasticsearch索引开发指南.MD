# elasticsearch索引开发指南
### 1.创建映射(goodsMapping)
在com.enation.app.javashop.deploy.service.impl.EsDeployExecutor#createGoodsMapping方法中新增所需字段

###2.填充商品信息
​ 在com.enation.app.javashop.core.goodssearch.service.impl.GoodsIndexManagerImpl#getSource方法中添加对应字段，将商品对应字段值信息存储到ES中

​ com.enation.app.javashop.core.goodssearch.model.GoodsIndex 这个类中也需要新增对应字段

### 3.商品搜索参数（如果新增字段不做为搜索参数可忽略此步骤）
​ 在com.enation.app.javashop.core.goodssearch.service.impl.GoodsSearchManagerImpl#createQuery方法中，添加对应字段的搜索表达式

> 说明：ES搜索新增字段之后，需要执行部署程序重新安装ES，否则搜索不到新增字段数据