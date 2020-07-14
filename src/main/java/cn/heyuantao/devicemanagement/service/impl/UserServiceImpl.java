package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.UserMapper;
import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.Weekend;
import tk.mybatis.mapper.weekend.WeekendCriteria;

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
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.orEqualTo("name",oneUser.getName());
        criteria.orEqualTo("email",oneUser.getEmail());
        if(userMapper.selectByExample(example).size()>0){
            throw new ServiceParamValidateException("存在具有相同注册信息的用户!");
        }
        userMapper.insert(oneUser);
        return oneUser;
    }

    @Override
    public List<User> getUsersByParams(Map<String, Object> params) {

        return userMapper.selectByParams(params);
    }

    @Override
    public User getUsersById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(user==null){
            throw new ServiceParamValidateException("该用户数据不存在");
        }
        return user;
    }

    @Override
    public void deleteById(Integer id) {
        User user = getUsersById(id);
        userMapper.delete(user);
    }

    @Override
    public User updateById(Integer id, User userData) {
        User user = getUsersById(id);

        Weekend<User> weekend =new Weekend<>(User.class);
        WeekendCriteria<User,Object> weekendCriteria= weekend.weekendCriteria();
        weekendCriteria.orEqualTo(User::getName,userData.getName()).orEqualTo(User::getEmail,userData.getEmail());

        Example example= new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id",id);
        weekend.and(criteria);
        
        if(userMapper.selectByExample(example).size()>0){
            throw new ServiceParamValidateException("存在具有相同注册信息的用户!");
        }

        userData.setId(user.getId());
        userData.setPassword(user.getPassword());
        userMapper.updateByPrimaryKey(userData);
        return userData;
    }
}
