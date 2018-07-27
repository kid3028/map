package com.map.mapper;

import com.map.entity.BeanMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BeanMapMapper {
    List<Map<String, Object>> findAll();

    List<Map<String, Object>> findByLonAndLatRange(Map<String, Object> mapRange);

    void insertList(@Param("beanMapList") List<BeanMap> beanMapList);
}