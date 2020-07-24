package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author he_yu
 */
@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    /**
     *
     * @param name
     * @return
     */
    Owner findOwnerByName(String name);
}
