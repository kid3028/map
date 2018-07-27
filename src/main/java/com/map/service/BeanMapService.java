package com.map.service;

import java.util.List;
import java.util.Map;

/**
 * @Author qull
 * @Discirption
 * @Date 2018/7/27 15:28
 */
public interface BeanMapService {

    List<Map<String, Object>> findByLonAndLatRange(Map<String, Object> mapRange);

}
