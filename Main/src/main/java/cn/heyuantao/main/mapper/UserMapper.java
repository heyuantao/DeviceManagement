package cn.heyuantao.main.mapper;

import cn.heyuantao.main.domain.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;


/**
 * @author he_yu
 */
@org.apache.ibatis.annotations.Mapper
public interface UserMapper extends Mapper<User>{

    List<User> selectByParams(Map<String, Object> params);
}
