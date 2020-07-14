package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.mapper.UserMapper;
import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author he_yu
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User addUser(User oneUser) {
        userMapper.insert(oneUser);
        return oneUser;
    }

    @Override
    public List<User> getUsersByParams(Map<String, Object> params) {

        return userMapper.selectByParams(params);
    }
}
