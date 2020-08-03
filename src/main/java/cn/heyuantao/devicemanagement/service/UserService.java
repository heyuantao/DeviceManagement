package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.UserMapper;
import cn.heyuantao.devicemanagement.domain.User;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * @author he_yu
 */

@CacheConfig(cacheNames = "UserService")
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;


    @Cacheable
    public List<User> getUsers() {
        return userMapper.selectAll();
    }

    @CacheEvict(allEntries = true)
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

    @Cacheable
    public List<User> getUsersByParams(Map<String, Object> params) {

        return userMapper.selectByParams(params);
    }


    @Cacheable
    public User getUsersById(Long id) {
        User user = userMapper.selectByPrimaryKey(id);
        if(user==null){
            throw new ServiceParamValidateException("该用户数据不存在");
        }
        return user;
    }

    @CacheEvict(allEntries = true)
    public void deleteById(Long id) {
        User user = getUsersById(id);
        userMapper.delete(user);
    }

    @CacheEvict(allEntries = true)
    public User updateById(Long id, User userData) {
        User user = getUsersById(id);

        Example example= new Example(User.class);
        Example.Criteria criteria = example.createCriteria();

        if(userData.getEmail()!=null){
            criteria.andNotEqualTo("id",id);
            criteria.andEqualTo("email",userData.getEmail());
            if(userMapper.selectByExample(example).size()>0){
                throw new ServiceParamValidateException("存在具有相同邮箱用户!");
            }
            user.setEmail(userData.getEmail());
        }


        if(userData.getPassword()!=null){
            user.setPassword(passwordHash(userData.getPassword()));
        }

        userMapper.updateByPrimaryKey(user);
        return user;
    }


    @Cacheable
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


    public String passwordHash(String rawPassword) {
        //BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(11);
        //return bCryptPasswordEncoder.encode(rawPassword);
        return passwordEncoder.encode(rawPassword);
    }
}
