package com.enation.app.javashop.deploy.service.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kingapex on 2019-02-14.
 *
 * @author kingapex
 * @version 1.0
 * @since 7.1.0
 * 2019-02-14
 */
public class MyMap {
    private Map map;

    public MyMap() {
        map = new HashMap();
    }

    public MyMap put(Object key,Object value) {
        map.put(key, value);
        return this;
    }

    public Map getMap() {

        return map;
    }
}
