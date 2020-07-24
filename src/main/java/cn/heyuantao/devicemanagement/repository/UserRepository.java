package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author he_yu
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     *通过查找用户名，返回该用户实例
     * @param name
     * @return
     */
    User findUserByName(String name);
}
