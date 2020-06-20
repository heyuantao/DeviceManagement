package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.domain.User;

import java.util.List;

public interface UserRepository {
    User saveOrUpdate(User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> listUsers();
}
