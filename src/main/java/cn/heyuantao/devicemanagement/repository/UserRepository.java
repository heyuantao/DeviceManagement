package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author he_yu
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    /**
     *
     * @param name
     * @return
     */
    public User findUserByName(String name);
}
