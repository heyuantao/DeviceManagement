package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.Device;
import org.springframework.data.repository.CrudRepository;

/**
 * @author he_yu
 */
public interface DeviceRepository extends CrudRepository<Device,Integer> {
}
