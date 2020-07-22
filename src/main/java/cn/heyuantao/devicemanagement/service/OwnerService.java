package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Owner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
public interface OwnerService {
    /**返回所有的设备所有人
     * @return
     */
    List<Owner> getOwners();

    /**通过map来进行查询
     * @param map
     * @return
     */
    List<Owner> getOwnersByParams(Map<String,Object> map);

    /**添加一个设备所有人
     * @param oneOwner
     * @return
     */
    Owner addOwner(Owner oneOwner);


    /**查找设备所有人
     * @param id
     * @return
     */
    Owner getOwnerById(Integer id);

    /**更新设备所有人
     * @param id
     * @param oneOwner
     * @return
     */
    Owner updateOwnerById(Integer id, Owner oneOwner);

    /**删除设备所有人
     * @param id
     */
    void deleteById(Integer id);
}
