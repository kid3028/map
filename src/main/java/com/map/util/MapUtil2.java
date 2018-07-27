package com.map.util;

import org.locationtech.spatial4j.context.SpatialContext;
import org.locationtech.spatial4j.distance.DistanceUtils;
import org.locationtech.spatial4j.shape.Rectangle;
import org.locationtech.spatial4j.shape.impl.PointImpl;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * 地图工具类：
 *      1、计算当前位置指定范围的经纬度
 *      2、计算多点与当前位置的距离集合
 */
public class MapUtil2 {
    // 对应数据库经度字段
    private static final String LON = "lon";

    // 对应数据库纬度字段
    private static final String LAT = "lat";

    // 对应前端显示所需的距离字段
    private static final String DISTANCE = "distance";

    // 对应前端显示所需要的经纬组合数组字段
    private static final String LNGLAT = "lnglat";

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
        Map<String, Object> mapRange = new HashMap<>();
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
     * @param list 周边目标集合
     * @return 目标距离集合
     */
    public static List<Map<String, Object>> getDistance(double currentLon, double currentLat, List<Map<String, Object>> list){
        SpatialContext geo = SpatialContext.GEO;
        // 如果当前周边目标集合不空进行计算，否则直接返回空
        if(!CollectionUtils.isEmpty(list)) {
            double distance = 0;
            for (Map<String, Object> bean : list) {  // 遍历周边目标集合
                // 计算距离
                distance = geo.calcDistance(new PointImpl(currentLon, currentLat, geo), new PointImpl((double)bean.get(LON), (double)bean.get(LAT), geo))
                        * DistanceUtils.DEG_TO_KM;

                bean.put(DISTANCE, distance);
                bean.put(LNGLAT, new double[]{(double) bean.get(LON), (double) bean.get(LAT)});

            }
            // 对目标距离集合进行按照距离进行升序排列
            Collections.sort(list, (Map<String,Object> b1, Map<String,Object> b2) -> {
                if ((double)b1.get(DISTANCE) >(double) b2.get(DISTANCE)) {
                    return 1;
                } if((double)b1.get(DISTANCE) >(double) b2.get(DISTANCE)) {
                    return -1;
                } else {
                    return 0;
                }
                });

            return list;
        }

        return null;
    }

}
