package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.mapper.OwnerMapper;
import cn.heyuantao.devicemanagement.mapper.UserMapper;
import cn.heyuantao.devicemanagement.service.OwnerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author he_yu
 */
@Service
public class OwnerServiceImpl implements OwnerService {
    @Resource
    OwnerMapper ownerMapper;

    @Override
    public List<Owner> getOwners() {
        return ownerMapper.selectAll();
    }

    @Override
    public Owner addOwner(Owner oneOwner) {
        ownerMapper.insert(oneOwner);
        return oneOwner;
    }

    @Override
    public Owner getOwnerById(Integer id){
        return ownerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        int id = ownerMapper.updateByPrimaryKey(owner);
        return ownerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        ownerMapper.deleteByPrimaryKey(id);
    }
}
