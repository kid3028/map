package com.map.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: qull
 * @Desciptiong:
 * @Date: Create in 2018/7/14 10:28
 */
public class MapDataUtil {
    private static final double LONG = 000;     // TODO  中心经度
    private static final double LAT = 000;      // TODO   中心纬度
    private static final double RADIUS = 20000;     // 搜索半径 单位m
    private static final String TYPES = "120000";   // 高德地图查POI
    private static final String KEYWORDS = "商务住宅";   // 搜索类型
    private static final String KEY = "aaaa";    // TODO  开发者key

        public static List<BeanMap> getData()
        {

            try {
                URL url = new URL(getUrl());
                URLConnection urlConnection = url.openConnection();
                HttpURLConnection connection = null;
                if (urlConnection instanceof HttpURLConnection) {
                    connection = (HttpURLConnection) urlConnection;
                } else {
                    System.out.println("输入urlַ");
                    return null;
                }
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuilder urlString = new StringBuilder();
                String current;
                while ((current = in.readLine()) != null) {
                    urlString.append(current);
                }

                JSONObject result = JSON.parseObject(urlString.toString());
                System.out.println(result);

                 JSONArray pois = result.getJSONArray("pois");

                JsonMap jsonMap = null;



                BeanMap beanMap = new BeanMap();
                List<BeanMap> beanMapList = new ArrayList<>();
                BeanMap center = new BeanMap("center5","aaa", LONG,LAT, null, "中心范围20km搜索商务住宅");
                beanMapList.add(center);
                for (int i = 0; i < pois.size(); i++) {
                    jsonMap = pois.getJSONObject(i).toJavaObject(JsonMap.class);
                    beanMap.setName(jsonMap.getName());
                    beanMap.setLocalPosition(jsonMap.getPname() + jsonMap.getCityname() + jsonMap.getAdname() + jsonMap.getAddress());
                    String[] lnglat = jsonMap.getLocation().split(",");
                    beanMap.setLon(Double.parseDouble(lnglat[0]));
                    beanMap.setLat(Double.parseDouble(lnglat[1]));
                    beanMap.setRemarks(center.getRemarks());
                    System.out.println(beanMap);
                    beanMapList.add(beanMap);
                }
                return beanMapList;

            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("请求异常！");
            }
            return  null;
        }


    public static String getUrl() {

        try {
            String encodeUrl = URLEncoder.encode(KEYWORDS, "UTF-8");
            String url = "http://restapi.amap.com/v3/place/around?key=" + KEY + "&location=" + LONG + "," + LAT + "&keywords=" + encodeUrl + "&types=" + TYPES + "&radius=" + RADIUS + "&offset=20&page=1&extensions=all";
            System.out.println(url);
            return url;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(getData());
    }






}
