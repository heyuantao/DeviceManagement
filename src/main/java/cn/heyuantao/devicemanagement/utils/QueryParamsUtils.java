package cn.heyuantao.devicemanagement.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author he_yu
 * 处理输入的参数
 */
public class QueryParamsUtils {
    public static Map<String,Object> formatRequestParams(Map<String,String> originMap){
        Map<String,Object> finalMap = new HashMap<String,Object>();

        Map<String,Object> selectMap = new HashMap<String,Object>();
        Map<String,Object> searchMap = new HashMap<String,Object>();
        for(Map.Entry<String,String> entry: originMap.entrySet()){

            if(!entry.getKey().equals("search")){
                selectMap.put(entry.getKey(),entry.getValue());
            }
            if(entry.getKey().equals("search")){
                //searchMap.put("%"+entry.getKey(),entry.getValue()+"%");
                searchMap.put(entry.getKey(),entry.getValue());
            }
        }

        finalMap.put("selectMap",selectMap);
        finalMap.put("searchMap",searchMap);
        return finalMap;
    }

}
