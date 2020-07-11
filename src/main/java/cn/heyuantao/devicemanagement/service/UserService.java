package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author he_yu
 */
public interface UserService {
    public List<User> getUsers();
    public User addUser(User oneUser);
}
