package cn.heyuantao.devicemanagement.util;

import com.github.pagehelper.PageInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

/**
 * @author he_yu
 * 该模块用户处理对list类型接口的返回数据，该类接口通常需要分页
 */
@Data
@NoArgsConstructor
public class CustomItemPagination implements Serializable {
    private Integer pageSize;
    private Integer pageNum;
    private Integer pages;
    private List<?> data;

    public CustomItemPagination(List<?> data, PageInfo<?> pageInfo){
        this.pageSize = pageInfo.getPageSize();
        this.pageNum = pageInfo.getPageNum();
        this.pages = pageInfo.getPages();
        this.data = data;
    }

/*    public CustomItemPagination getPaginatedData(){
        return this;
    }*/
/*    private Map<String,Object> returnMap;
    public CustomItemPagination(List<?> data, PageInfo<?> pageInfo){
        this.returnMap=new HashMap<>();
        this.returnMap.put("data",data);
        this.returnMap.put("pageSize",pageInfo.getPageSize());
        this.returnMap.put("pageNum",pageInfo.getPageNum());
        this.returnMap.put("pages",pageInfo.getPages());

    }
    public Map<String,Object> getPaginatedData(){
        return this.returnMap;
    }*/
}
