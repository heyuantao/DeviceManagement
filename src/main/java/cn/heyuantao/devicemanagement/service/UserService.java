package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
public interface UserService {
    public List<User> getUsers();
    public User addUser(User oneUser);

    List<User> getUsersByParams(Map<String, Object> params);

    User getUsersById(Integer id);

    void deleteById(Integer id);

    User updateById(Integer id, User convertToDO);

    User getUserByName(String username);
}
