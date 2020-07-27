package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.dto.DeviceRequestDTO;
import cn.heyuantao.devicemanagement.mapper.DeviceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
@Service
public class DeviceService {
    @Resource
    DeviceMapper deviceMapper;
    @Resource
    OwnerService ownerService;
    @Resource
    TypeService typeService;
    @Resource
    LocationService locationService;

/*    public List<Device> getDevices() {
        System.out.println("Device information !");
        return deviceMapper.getDevicesInformation();
    }*/

    public Device addDevice(Device device) {
        deviceMapper.insert(device);
        return device;
    }

/*    public Device addDevice(DeviceRequestDTO deviceRequestDTO){
        Device device = new Device();
        BeanUtils.copyProperties(deviceRequestDTO,device);
        device.setInDate(new Date(System.currentTimeMillis()));
        device.setUpdated(new Date(System.currentTimeMillis()));

        device.setOwner(ownerService.getOwnerByName(deviceRequestDTO.getOwner__name()));
        device.setType(typeService.getTypeByName(deviceRequestDTO.getType__name()));
        device.setLocation(locationService.getLocationByName(deviceRequestDTO.getLocation__name()));

        return addDevice(device);
    }*/

    public List<Device> selectDevicesByParams(Map<String, Object> params) {
        return deviceMapper.selectByParams(params);
    }

    public List<Device> filterDeviceByParams(String filterString){
        return deviceMapper.filterByParams(filterString);
    }
}
