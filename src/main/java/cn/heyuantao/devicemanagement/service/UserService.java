package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public List<User> getUsers();
}
