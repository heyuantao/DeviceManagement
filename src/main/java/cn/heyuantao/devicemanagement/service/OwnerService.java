package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Owner;

import java.util.List;

/**
 * @author he_yu
 */
public interface OwnerService {
    List<Owner> getOwners();
    Owner addOwner(Owner oneOwner);
}
