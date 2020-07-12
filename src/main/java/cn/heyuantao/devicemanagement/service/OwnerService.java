package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Owner;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
public interface OwnerService {
    List<Owner> getOwners();
    List<Owner> getOwnersByParams(Map map);
    Owner addOwner(Owner oneOwner);

    //Owner updateOwner(Owner owner);
    Owner getOwnerById(Integer id);
    Owner updateOwnerById(Integer id, Owner oneOwner);
    void deleteById(Integer id);
}
