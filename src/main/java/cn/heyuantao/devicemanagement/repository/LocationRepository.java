package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author he_yu
 */
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {



    /**
     *使用用户名进行分页查找
     * @param name
     * @return
     */
    List<Location> findLocationByName(String name);

    /**
     * 使用使用各类信息进行查找
     * @param name
     * @param pageable
     * @return
     */
    Page<Location> findLocationsByNameOrRoomNumberOrderByAddress(String name, Pageable pageable);

}
