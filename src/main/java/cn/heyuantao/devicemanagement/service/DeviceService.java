package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.mapper.DeviceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
@Service
public class DeviceService {
    @Resource
    DeviceMapper deviceMapper;

/*    public List<Device> getDevices() {
        System.out.println("Device information !");
        return deviceMapper.getDevicesInformation();
    }*/

    public Device addDevice(Device device) {
        deviceMapper.insert(device);
        return device;
    }

    public List<Device> selectDevicesByParams(Map<String, Object> params) {
        return deviceMapper.selectByParams(params);
    }

    public List<Device> filterDeviceByParams(String filterString){
        return deviceMapper.filterByParams(filterString);
    }
}
