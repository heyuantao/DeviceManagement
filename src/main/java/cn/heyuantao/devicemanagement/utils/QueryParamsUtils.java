package cn.heyuantao.devicemanagement.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author he_yu
 * 处理针对list这类api接口的参数数据，这种接口通常会包含过滤和查找两类参数，即"过滤"和"查找"，并分别存放在filterMap和searchMap中。"过滤"和"查找"只能二选一，
 * 且"查找"要有更高的优先级
 *
 * "过滤"要包含列名和过滤条件，例如针对url的get方法中的参数含有"name=abc&department=research"的内容,此时将这些值分为key和value的形式存放在filterMap中，
 * 并在Mybatis的*Mapper.xml文件中用IF标签将filterMap的值转变为对应的select语句中的精确查找。例子如下：
 * 原始的查询语句为："name=abc&department=research"
 * 在filterMap中的内容为:{"name":"abc","department":"research"}
 * 例如select * from table where name=abc and department=research
 *
 * "查找"仅仅包含查找条件，例如针对url的get方法中的参数含有"search=abc"的内容，此时将其转变为含有一个元素的map实例，并存储在searchMap中，后续在Mybatis的
 * 的*Mapper.xml文件中用IF标签将searchMap的值转变为对应的模糊查找。例子如下：
 * 原始的查询语句为："search=abc"
 * 在searchMap中内容为：{"search":"abc"}
 * 转变后的SQL语句为：select * from table where name like '%abc%' or department like '%abc%'。
 * 其中列名在*Mapper.xml文件中指定
 */

public class QueryParamsUtils {
    public static Map<String,Object> formatRequestParams(Map<String,String> originMap){
        Map<String,Object> finalMap = new HashMap<String,Object>();

        Map<String,Object> filterMap = new HashMap<String,Object>();
        Map<String,Object> searchMap = new HashMap<String,Object>();
        for(Map.Entry<String,String> entry: originMap.entrySet()){

            if(!entry.getKey().equals("search")){
                filterMap.put(entry.getKey(),entry.getValue());
            }
            if(entry.getKey().equals("search")){
                searchMap.put(entry.getKey(),entry.getValue());
            }
        }
/*        if(searchMap.size()>0){
            finalMap.put("searchMap",searchMap);
        }else {
            finalMap.put("filterMap",filterMap);
        }*/
        finalMap.put("searchMap",searchMap);
        finalMap.put("filterMap",filterMap);
        return finalMap;
    }

}
