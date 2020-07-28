package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.dto.DeviceRequestDTO;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.DeviceMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        Device theDevice = null;
        try {
            theDevice = selectByAssetNo(device.getAssetNo());
        }catch (Exception ex){
            theDevice = null;
        }

        if(theDevice==null){
            deviceMapper.addDevice(device);
        }else{
            throw new ServiceParamValidateException("要添加的设备已经存在");
        }
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


    /**
     * 检查是否有相同资产编号的设备，如果存在则会抛出异常
     * @param assetNo
     * @return
     */
    public Device selectByAssetNo(String assetNo){
        Example example = new Example(Device.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("assetNo",assetNo);
        List<Device> deviceList = deviceMapper.selectByExample(example);
        if(deviceList.size()==0){
            throw new ServiceParamValidateException("资产编号为"+assetNo+"的设备不存在!");
        }
        return deviceList.get(0);
    }

    public List<Device> selectDevicesByParams(Map<String, Object> params) {
        return deviceMapper.selectByParams(params);
    }

    public List<Device> filterDeviceByParams(String filterString){
        return deviceMapper.filterByParams(filterString);
    }
}
