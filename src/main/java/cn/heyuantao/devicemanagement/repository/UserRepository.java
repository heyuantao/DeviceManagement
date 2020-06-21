package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<User, Long> {
}
