package com.enation.app.javashop.deploy;

import com.enation.app.javashop.deploy.service.impl.MyMap;
import com.enation.app.javashop.framework.elasticsearch.DefaultEsTemplateBuilder;
import com.enation.app.javashop.framework.elasticsearch.EsTemplateBuilder;
import org.junit.Test;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kingapex
 * @version 1.0
 * @since 7.1.0
 * 2019-03-21
 */

public class ESTest {


    @Test
    public void test() {

        EsTemplateBuilder esTemplateBuilder = new DefaultEsTemplateBuilder().setClusterName("elasticsearch-cluster").setClusterNodes("192.168.2.100:32100");
        ElasticsearchTemplate esTemplate = esTemplateBuilder.build();

        String indexName = "javashop";
        esTemplate.deleteIndex(indexName);
        String ptIndexName = indexName+"_pt";
        String goodsIndexName = indexName+"_goods";
        esTemplate.createIndex(ptIndexName);
        esTemplate.createIndex(goodsIndexName);

        Map goodsMapping = createGoodsMapping();
        Map pinTuanMapping = createPingTuanMapping();


        //创建类型
        esTemplate.putMapping(goodsIndexName, "goods", goodsMapping);
        esTemplate.putMapping(ptIndexName, "pintuan_goods", pinTuanMapping);

    }



    /**
     * 创建商品mapping
     * @return
     */
    private Map createGoodsMapping() {

        Map goodsMap = new HashMap();

        goodsMap.put("goodsId", new MyMap().put("type", "long").getMap());
        goodsMap.put("goodsName", new MyMap().put("type", "text").put("analyzer", "ik_max_word").getMap());
        goodsMap.put("thumbnail", new MyMap().put("type", "text").getMap());
        goodsMap.put("small", new MyMap().put("type", "text").getMap());
        goodsMap.put("buyCount", new MyMap().put("type", "integer").getMap());
        goodsMap.put("sellerId", new MyMap().put("type", "integer").getMap());
        goodsMap.put("sellerName", new MyMap().put("type", "text").getMap());
        goodsMap.put("shopCatId", new MyMap().put("type", "integer").getMap());
        goodsMap.put("shopCatPath", new MyMap().put("type", "text").getMap());
        goodsMap.put("commentNum", new MyMap().put("type", "integer").getMap());
        goodsMap.put("grade", new MyMap().put("type", "double").getMap());
        goodsMap.put("price", new MyMap().put("type", "double").getMap());
        goodsMap.put("brand", new MyMap().put("type", "integer").getMap());
        goodsMap.put("categoryId", new MyMap().put("type", "integer").getMap());
        goodsMap.put("categoryPath", new MyMap().put("type", "text").getMap());
        goodsMap.put("disabled", new MyMap().put("type", "integer").getMap());
        goodsMap.put("marketEnable", new MyMap().put("type", "integer").getMap());
        goodsMap.put("isAuth", new MyMap().put("type", "integer").getMap());
        goodsMap.put("intro", new MyMap().put("type", "text").getMap());
        goodsMap.put("selfOperated", new MyMap().put("type", "integer").getMap());

        Map paramPro = new MyMap().put("name", new MyMap().put("type", "keyword").getMap()).put("value", new MyMap().put("type", "keyword").getMap()).getMap();
        goodsMap.put("params", new MyMap().put("type", "nested").put("properties", paramPro).getMap());

        return  new MyMap().put("properties",goodsMap).getMap() ;
    }

    /**
     * 创建拼团mapping
     * @return
     */
    private Map createPingTuanMapping() {

        Map pingTuanMap = new HashMap();
        pingTuanMap.put("buyCount", new MyMap().put("type", "integer").getMap());
        pingTuanMap.put("categoryId", new MyMap().put("type", "integer").getMap());
        pingTuanMap.put("categoryPath", new MyMap().put("type", "text").getMap());
        pingTuanMap.put("goodsId", new MyMap().put("type", "long").getMap());
        pingTuanMap.put("goodsName", new MyMap().put("type", "text").put("analyzer", "ik_max_word").getMap());
        pingTuanMap.put("originPrice", new MyMap().put("type", "double").getMap());
        pingTuanMap.put("salesPrice", new MyMap().put("type", "double").getMap());
        pingTuanMap.put("skuId", new MyMap().put("type", "long").getMap());
        pingTuanMap.put("thumbnail", new MyMap().put("type", "text").getMap());
        return  new MyMap().put("properties",pingTuanMap).getMap() ;
    }

}
