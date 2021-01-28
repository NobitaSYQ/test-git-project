package com.enation.app.javashop.deploy.util;

import com.enation.app.javashop.framework.exception.ServiceException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * @Author gy
 * @Description
 * @Date: Created in 1:32 2020/7/4 0004
 */
public class ObjectMapperUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 实体类转换map
     *
     * @param o
     * @return
     */
    public static Map toMap(Object o) {
        try {
            return objectMapper.readValue(objectMapper.writeValueAsString(o), Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new ServiceException("400", "json格式处理异常");

        }

    }
}