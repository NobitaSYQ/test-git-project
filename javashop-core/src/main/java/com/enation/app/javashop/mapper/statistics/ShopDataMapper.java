package com.enation.app.javashop.mapper.statistics;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.enation.app.javashop.framework.cache.MybatisRedisCache;
import com.enation.app.javashop.model.statistics.dto.ShopData;
import org.apache.ibatis.annotations.CacheNamespace;

/**
 * 店铺统计数据mapper
 * @author zs
 * @version v1.0
 * @since v7.2.2
 * 2020-08-03
 */
@CacheNamespace(implementation= MybatisRedisCache.class,eviction=MybatisRedisCache.class)
public interface ShopDataMapper extends BaseMapper<ShopData> {

}
