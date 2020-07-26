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

    /**
     *
     * @param params
     * @return
     */
    List<Device> selectByParams(Map<String, Object> params);

    /**
     * filter by device.name device.asset_no and owner.name
     * @param filterString
     * @return
     */
    List<Device> filterByParams(String filterString);
}
