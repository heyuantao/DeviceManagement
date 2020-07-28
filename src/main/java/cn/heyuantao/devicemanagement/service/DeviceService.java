package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.dto.DeviceRequestDTO;
import cn.heyuantao.devicemanagement.mapper.DeviceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
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

    /**
     * 添加一个设备对象，传入的device对象是pojo对象，带有对外键对象的关联
     * @param device
     * @return
     */
    public Device addDevice(Device device) {
        /**
         * 先检查设备对应的资产编号是否已经存在，否则不进行入库操作
         */
        //Map<String,Object> queryMap= new HashMap<String, Object>();
        //queryMap.put()


        deviceMapper.addDevice(device);
        return device;
    }

    /**
     * 更新一个设备的数据库记录
     * @param device
     * @return
     */
    public Device updateDevice(Device device){
        deviceMapper.updateDevice(device);
        return deviceMapper.selectByPrimaryKey(device.getId());
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
