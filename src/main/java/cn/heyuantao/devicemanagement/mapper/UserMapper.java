package cn.heyuantao.devicemanagement.mapper;

import cn.heyuantao.devicemanagement.domain.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


/**
 * @author he_yu
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User>{

    List<User> selectUserByParams(Map<String, Object> params);
}
