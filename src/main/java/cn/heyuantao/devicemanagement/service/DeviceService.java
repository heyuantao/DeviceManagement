package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.domain.Type;
import cn.heyuantao.devicemanagement.event.CrudAction;
import cn.heyuantao.devicemanagement.event.TypeChangeEvent;
import cn.heyuantao.devicemanagement.exception.ResourceNotFoundException;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.DeviceMapper;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import tk.mybatis.mapper.entity.Example;

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
            deviceMapper.insert(device);
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
    public Device updateDeviceById(Long id,Device device){
        device.setId(id);
        deviceMapper.updateDevice(device);
        return deviceMapper.selectById(id);
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

    public Device getDeviceById(Long id) {
        Device device = deviceMapper.selectById(id);
        if(device==null){
            throw new ServiceParamValidateException("记录为"+id+"的设备不存在");
        }
        System.out.println(device);
        return device;
    }

    public void deleteDeviceById(Long id) {
        Device device = deviceMapper.selectByPrimaryKey(id);
        if(device==null){
            throw new ServiceParamValidateException("记录为"+id+"的设备不存在");
        }
        deviceMapper.deleteByPrimaryKey(id);
    }

    private void deleteDeviceByTypeId(Long id) {
        Example example = new Example(Device.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("typeId",id);
        deviceMapper.deleteByExample(example);
    }


    /**
     * 处理设备类型编号的事件函数，当前主要处理设备的删除
     * @param event
     */
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    void handleTypeChangeEvent(TypeChangeEvent event){
        System.out.println("################事件发生#############");
        CrudAction action = event.getAction();
        Type instance = event.getInstance();
        System.out.println(action.getValue());
        System.out.println(event.getInstance());
        deleteDeviceByTypeId(instance.getId());
    }


/*    public Device updateDeviceById(Long id, Device convertToDO) {
    }*/
}
