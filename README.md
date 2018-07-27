1、修改/src/main/resources/application.properties

``` properties
# 修改为实际需要路径
server.servlet.path=/map
# 修改为实际需要端口
server.port=9898

#mvc
spring.mvc.view.prefix=classpath:/templates/
spring.mvc.view.suffix=.html
#datasource
# 修改为实际数据库信息
spring.datasource.driver-class-name=com.mysql.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/map?characterEncoding=utf-8&useSSL=false
spring.datasource.username=root
spring.datasource.password=123456
```

2、修改src/main/java/com/map/util/MapUtil2.java

``` java
    // 对应数据库经度字段
    private static final String LON = "lon";

    // 对应数据库纬度字段
    private static final String LAT = "lat";

    // 对应前端显示所需的距离字段
    private static final String DISTANCE = "distance";

    // 对应前端显示所需要的经纬组合数组字段
    private static final String LNGLAT = "lnglat";
```

注意：如果需要目前使用,不需要修改这两个属性
```java 
    // 对应前端显示所需的距离字段
    private static final String DISTANCE = "distance";

    // 对应前端显示所需要的经纬组合数组字段
    private static final String LNGLAT = "lnglat";
```

3、访问接口使用get请求，请求地址默认  
        http://localhost:9898/map/beanMap/list?tableName=customer&currentLon=102.719662&currentLat=25.038107&radius=0.5   
        参数说明：    
        currentLon 当前位置经度  
        currentLat 当前位置纬度  
        radius     搜索范围 单位KM   
        
        返回数据：  
```json
{
    "state": true,
    "data": [
        {
            "local_position": "云南省昆明市五华区人民中路17号百盛购物广场1层",
            "lnglat": [
                102.715492,
                25.041675
            ],
            "distance": 0.5778345058867401,
            "name": "肯德基(青年路店)",
            "lon": 102.715492,
            "id": 2,
            "lat": 25.041675,
            "remarks": "以center1为中心抓取"
        },
        {
            "local_position": "云南省昆明市盘龙区东风广场",
            "lnglat": [
                102.719662,
                25.038107
            ],
            "distance": 0,
            "name": "center1",
            "lon": 102.719662,
            "id": 1,
            "lat": 25.038107,
            "remarks": "以此为中心抓取5km内高德上肯德基数据"
        }
    ]
}
```
        
4、mysql数据库脚本src/main/resources/sql/customer.sql   

6、日志配置src/main/resources/logback.xml  

5、src/main/java/com/map/MapMybatisApplication.java  系统主入口   