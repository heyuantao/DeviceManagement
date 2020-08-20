package cn.heyuantao.main.util;

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
}
