package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author he_yu
 */
public interface TypeRepository extends JpaRepository<Type,Integer> {
    /**
     *
     * @param name
     * @return
     */
    Type findTypeByName(String name);
}
