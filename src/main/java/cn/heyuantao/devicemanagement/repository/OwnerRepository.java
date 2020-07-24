package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.Owner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author he_yu
 */
@Repository
public interface OwnerRepository extends CrudRepository<Owner,String> {
}
