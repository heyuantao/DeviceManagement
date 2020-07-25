package cn.heyuantao.devicemanagement.service;


import cn.heyuantao.devicemanagement.entity.Location;

import java.util.List;

/**
 * @author he_yu
 */
public interface LocationService {
    /**获取所有的设备保存地址
     * 获得所有位置
     * @return
     */
    List<Location> getLocations();

    /**添加一个设备存放地址
     * @param oneLocation
     * @return
     */
    Location addLocation(Location oneLocation);

    /**通过ID来获取设备保存地址
     * @param id
     * @return
     */
    Location getLocationById(Long id);

    /**更新设备保存地址
     *
     * @param id 位置在数据库的编号
     * @param location  位置对象
     * @return
     */
    Location updateLocationById(Long id, Location location);

    /**删除指定的存放位置
     * @param id
     */
    void deleteLocationById(Long id);
}
