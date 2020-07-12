package cn.heyuantao.devicemanagement.mapper;

import cn.heyuantao.devicemanagement.domain.Owner;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
@org.apache.ibatis.annotations.Mapper
public interface OwnerMapper extends Mapper<Owner> {
    List<Owner> selectByParams(Map<String,Object> map);
}
