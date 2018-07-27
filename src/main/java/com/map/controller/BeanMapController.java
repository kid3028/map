package com.map.controller;

import com.map.entity.ResultVO;
import com.map.mapper.BeanMapMapper;
import com.map.service.BeanMapService;
import com.map.util.MapUtil2;
import com.sun.org.glassfish.external.statistics.RangeStatistic;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: qull
 * @Desciptiong:
 * @Date: Create in 2018/7/13 21:38
 */
@Controller
@RequestMapping("/beanMap")
@Log
public class BeanMapController {
    private final BeanMapService beanMapService;

    @Autowired
    public BeanMapController(BeanMapService beanMapService) {
        Assert.notNull(beanMapService, "beanMapService must not be null");
        this.beanMapService = beanMapService;
    }

    @GetMapping()
    public String getBeanMap() {
        return "map2";
    }

    /**
     * 获取周边列表
     * @param currentLon  当前位置经度
     * @param currentLat  当前位置纬度
     * @param radius      搜索半径  单位KM
     * @return            周边列表
     */
    @GetMapping("/list")
    @ResponseBody
    public ResultVO<List<Map<String,Object>>> getBeanMapList(double currentLon, double currentLat, double radius) {

        Map<String, Object> mapRange = MapUtil2.getLonAndLatRange(currentLon, currentLat, radius);
        List<Map<String, Object>> beanbList = beanMapService.findByLonAndLatRange(mapRange);
        ResultVO ret =  new ResultVO<>(true,MapUtil2.getDistance(currentLon, currentLat, beanbList));
        return ret;
    }

}
