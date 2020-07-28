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
    /**
     * 获取设备的信息，当前暂时不用
     * @return
     */
    //List<Device> getDevicesInformation();


    /**
     * 更新该设备对应的记录，相应的参数存储在device.id中
     * @param device
     * @return
     */
    Device updateDevice(Device device);

    /**
     * 传入device的对象，包含相应的关联对象的引用，将其保存入数据库
     * @param device
     * @return
     */
    Long addDevice(Device device);

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
