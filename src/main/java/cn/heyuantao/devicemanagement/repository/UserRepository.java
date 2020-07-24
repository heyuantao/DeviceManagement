package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.User;
import org.springframework.data.repository.CrudRepository;


/**
 * @author he_yu
 */
public interface UserRepository extends CrudRepository<User,String> {
}
