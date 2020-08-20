package cn.heyuantao.main.mapper;

import cn.heyuantao.main.domain.Type;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
@org.apache.ibatis.annotations.Mapper
public interface TypeMapper extends Mapper<Type> {
    List<Type> selectByParams(Map<String, Object> params);
}
