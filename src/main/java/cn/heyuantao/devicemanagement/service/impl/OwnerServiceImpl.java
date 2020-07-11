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
}
