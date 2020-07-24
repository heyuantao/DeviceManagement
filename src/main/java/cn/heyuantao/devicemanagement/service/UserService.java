package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
public interface UserService {
    /**获取所有的系统用户
     * @return
     */
    public List<User> getUsers();

    /**添加系统用户
     * @param oneUser
     * @return
     */
    public User addUser(User oneUser);

    /**根据Map来查找
     * @param params
     * @return
     */
    List<User> getUsersByParams(Map<String, Object> params);

    /**根据ID来查找用户
     * @param id
     * @return
     */
    User getUsersById(Integer id);

    /**
     * 根据ID来删除用户
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 根据ID来更新用户
     * @param id
     * @param user
     * @return
     */
    User updateById(Integer id, User user);

    /**
     * 根据用户来查找用户
     * @param username
     * @return
     */
    User getUserByName(String username);

    /**
     * 对原始密码进行加密
     * @param rawPassword 原始的密码
     * @return 加密后的密码
     */
    public String passwordHash(String rawPassword);
}
