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
     * @return 返回值表示更新数据是否正确
     */
    Boolean updateDevice(Device device);

    /**
     * 传入device的对象，包含相应的关联对象的引用，将其保存入数据库，新生成记录的主键存放在device.id中
     * @param device
     * @return 返回值表示此次插入是否正确
     */
    Boolean addDevice(Device device);

    /**
     *
     * @param params
     * @return
     */
    List<Device> selectByParams(Map<String, Object> params);

    /**
     * 根据id编号来返回POJO对象
     * @param id
     * @return
     */
    Device selectById(Long id);


    /**
     * filter by device.name device.asset_no and owner.name
     * @param filterString
     * @return
     */
    List<Device> filterByParams(String filterString);
}
