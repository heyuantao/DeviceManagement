package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author he_yu
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    /**
     *
     * @param name
     * @return
     */
    Location findLocationByName(String name);
}
