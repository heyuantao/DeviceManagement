package cn.heyuantao.devicemanagement.utils;

import com.github.pagehelper.PageInfo;

import java.util.*;

/**
 * @author he_yu
 */
public class CustomItemPagination {
    private Map<String,Object> returnMap;
    public CustomItemPagination(List<?> data, PageInfo<?> pageInfo){
        this.returnMap=new HashMap<>();
        this.returnMap.put("data",data);
        this.returnMap.put("pageSize",pageInfo.getPageSize());
        this.returnMap.put("pageNum",pageInfo.getPageNum());
        this.returnMap.put("pages",pageInfo.getPages());

    }
    public Map<String,Object> get_paginated_data(){
        return this.returnMap;
    }
}
