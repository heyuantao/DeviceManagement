package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Owner;

import java.util.List;

/**
 * @author he_yu
 */
public interface OwnerService {
    List<Owner> getOwners();
    Owner addOwner(Owner oneOwner);

    //Owner updateOwner(Owner owner);
    Owner getOwnerById(Integer id);
    Owner updateOwnerById(Integer id, Owner oneOwner);
    void deleteById(Integer id);
}
