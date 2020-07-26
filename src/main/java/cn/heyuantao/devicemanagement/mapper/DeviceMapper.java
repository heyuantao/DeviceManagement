package cn.heyuantao.devicemanagement.mapper;

import cn.heyuantao.devicemanagement.domain.Device;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
@org.apache.ibatis.annotations.Mapper
public interface DeviceMapper extends Mapper<Device> {
    List<Device> getDevicesInformation();

    List<Device> selectByParams(Map<String, Object> params);

    List<Device> filterByParams(String filterString);
}
