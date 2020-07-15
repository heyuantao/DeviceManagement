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


    //private Example example= new Example(User.class);

/*    public UserServiceImpl() {
        this.example = new Example(User.class);
    }*/

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User addUser(User oneUser) {
        Example example = new Example(User.class);
        example.clear();
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


/*        Example example= new Example(User.class);
        Example.Criteria criteria1 = example.createCriteria();
        criteria1.orEqualTo("name",user.getName());
        criteria1.orEqualTo("email",user.getEmail());

        Example.Criteria criteria2 = example.createCriteria();
        criteria2.andNotEqualTo("id",id);
        example.and(criteria2);*/
        Example example= new Example(User.class);
        example.clear();
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id",id);
        criteria.andEqualTo("name",userData.getName());
        if(userMapper.selectByExample(example).size()>0){
            throw new ServiceParamValidateException("存在具有相同用户名的用户!");
        }

        example.clear();
        criteria = example.createCriteria();
        criteria.andNotEqualTo("id",id);
        criteria.andEqualTo("email",userData.getEmail());
        if(userMapper.selectByExample(example).size()>0){
            throw new ServiceParamValidateException("存在具有相同邮箱用户!");
        }

        userData.setId(user.getId());
        userData.setPassword(user.getPassword());
        userMapper.updateByPrimaryKey(userData);
        return userData;
    }

    @Override
    public User getUserByName(String username) {
        Example example=new Example(User.class);
        example.clear();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",username);
        List<User> userList = userMapper.selectByExample(example);
        if(userList.size()==0){
            throw new ServiceParamValidateException("未找到名字为 "+username+" 的用户");
        }
        return userList.get(0);
    }
}
