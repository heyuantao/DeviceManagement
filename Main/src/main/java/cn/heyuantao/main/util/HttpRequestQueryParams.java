package cn.heyuantao.main.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
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

public class HttpRequestQueryParams {

    /**
     * 存放select的信息,key为列名,value为列的值
     */
    private Map<String,Object> selectMap;

    /**
     * 存放是否包含过滤的状态和过滤的字符串
     */
    private String filterValue;
    private Boolean needFilter;

    public HttpRequestQueryParams(HttpServletRequest httpServletRequest){
        selectMap = new HashMap<String, Object>();
        filterValue= "";
        needFilter = Boolean.FALSE;

        Map<String,String[]> rawParams = httpServletRequest.getParameterMap();
        Map<String,Object> finalParams = new HashMap<>();
        for(Iterator<String> iter = rawParams.keySet().iterator(); iter.hasNext();){
            String name = (String) iter.next();
            String[] values = (String[]) rawParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
            }
            if(StringUtils.equalsIgnoreCase(name,"search")){
                needFilter = Boolean.TRUE;
                filterValue = valueStr;
            }else{
                selectMap.put(name, valueStr);
            }
        }
    }

    public Boolean hasFilterParams(){
        return needFilter;
    }

    public String getFilterString(){
        return filterValue;
    }

    public Map<String,Object> getSelectMap(){
        return selectMap;
    }
}
