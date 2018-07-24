package com.map.util;

import com.map.entity.Customer;
import com.map.entity.CustomerVO;
import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 地图工具类：
 *      1、计算当前位置指定范围的经纬度
 *      2、计算多点与当前位置的距离集合
 */
public class MapUtil {
    /**
     * 获取以指定经纬位置为圆心指定直线距离为半径的圆形成的外包矩形范围
     * @param currentLon 当前位置经度
     * @param currentLat 当前位置纬度
     * @param radius    查找直线距离  KM
     * @return          外包矩形
     */
    public static Map<String, Object> getLonAndLatRange(double currentLon, double currentLat, double radius) {
        // 获取到SpatialContext
        SpatialContext geo = SpatialContext.GEO;
        // 获取外包矩形    DistanceUtils.KM_TO_DEG--->指定单位为千米KM
        Rectangle rectangle = geo.getDistCalc().calcBoxByDistFromPt(new PointImpl(currentLon, currentLat, geo), radius * DistanceUtils.KM_TO_DEG, geo, null);
        System.out.println("minX:" + rectangle.getMinX() + "    maxX:" + rectangle.getMaxX()); // lon经度
        System.out.println("minY:" + rectangle.getMinY() + "      maxY:" + rectangle.getMaxY()); // lat 纬度
        Map<String, Object> mapRange = new HashMap<>();
      //  Map<String, Double> mapRange = new HashMap<>();
        mapRange.put("minX", rectangle.getMinX());
        mapRange.put("maxX", rectangle.getMaxX());
        mapRange.put("minY", rectangle.getMinY());
        mapRange.put("maxY", rectangle.getMaxY());
        return mapRange;
    }

    /**
     * 计算当前位置与周边目标之间的直线距离，并按照距离升序返回
     * @param currentLon 当前位置经度
     * @param currentLat 当前位置纬度
     * @param customerList 周边目标集合
     * @return 目标距离集合
     */
    public static List<CustomerVO> getDistance(double currentLon, double currentLat, List<Customer> customerList){
        SpatialContext geo = SpatialContext.GEO;
        // 如果当前周边目标集合不空进行计算，否则直接返回空
        if(!CollectionUtils.isEmpty(customerList)) {
//            Map<Object, Double> distanceMap = new HashMap<>();
            double distance = 0;
            List<CustomerVO> sortDistanceList = new ArrayList<>();
            CustomerVO customerVO = null;
            for (Customer customer : customerList) {  // 遍历周边目标集合
                // 计算距离
                distance = geo.calcDistance(new PointImpl(currentLon, currentLat, geo), new PointImpl(customer.getLon(), customer.getLat(), geo))
                        * DistanceUtils.DEG_TO_KM;

                customerVO = new CustomerVO();
                BeanUtils.copyProperties(customer, customerVO);
                customerVO.setDistance(distance);
                customerVO.setLnglat(new double[]{customer.getLon(), customer.getLat()});
                sortDistanceList.add(customerVO);
                System.out.println(distance);
//                distanceMap.put(customer.getId(), distance);
            }
            // 对目标距离集合进行按照距离进行升序排列
            Collections.sort(sortDistanceList, (CustomerVO c1, CustomerVO c2) -> {
                if (c1.getDistance() > c2.getDistance()) {
                    return 1;
                } if(c1.getDistance() < c2.getDistance()) {
                    return -1;
                } else {
                    return 0;
                }
                });
            System.out.println("sortDistanceList------>" + sortDistanceList);
//            return distanceMap;
            return sortDistanceList;
        }

        return null;
    }

}
