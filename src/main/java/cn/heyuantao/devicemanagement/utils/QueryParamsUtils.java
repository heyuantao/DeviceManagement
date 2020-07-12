package cn.heyuantao.devicemanagement.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            /*
            应当检查Key的情况，防止出现注入攻击
             */
            if(!entry.getKey().equals("search")){
                selectMap.put(entry.getKey(),entry.getValue());
            }
            if(entry.getKey().equals("search")){
                searchMap.put("%"+entry.getKey(),entry.getValue()+"%");
            }
        }

        finalMap.put("selectMap",selectMap);
        finalMap.put("searchMap",searchMap);
        return finalMap;
    }

/*    public Boolean isNotSafeString(String string){
        //String reg="([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]){6,8}$";
        String reg="([A-Z]|[a-z]|[0-9]|[_-]){6,8}$";
        Pattern pAll=Pattern.compile(reg);
        Matcher mAll = pAll.matcher(str);

    }*/
}
