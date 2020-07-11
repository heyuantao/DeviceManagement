package cn.heyuantao.devicemanagement.mapper;

import cn.heyuantao.devicemanagement.domain.Device;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface DeviceMapper extends Mapper<Device> {
    List<Device> getDevicesInformation();
}
