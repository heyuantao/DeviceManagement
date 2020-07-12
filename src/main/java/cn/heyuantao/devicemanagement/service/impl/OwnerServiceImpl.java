package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.exception.ResourceNotFoundException;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.OwnerMapper;
import cn.heyuantao.devicemanagement.service.OwnerService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
        Example example = new Example(Owner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",oneOwner.getName());
        List<Owner> userList=ownerMapper.selectByExample(example);
        if(userList.size()>1){
            throw new ServiceParamValidateException("数据库中已经存在名为" +oneOwner.getName()+"的用户！");
        }
        ownerMapper.insert(oneOwner);
        return oneOwner;
    }

    @Override
    public Owner getOwnerById(Integer id){
        Owner oneOwner = ownerMapper.selectByPrimaryKey(id);
        if(oneOwner==null){
            throw new ResourceNotFoundException("该设备所有者不存在 !");
        }

        return oneOwner;
    }

    @Override
    public Owner updateOwner(Owner oneOwner) {
        Example example = new Example(Owner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",oneOwner.getName());
        criteria.andNotEqualTo("id",oneOwner.getId());
        List<Owner> userList=ownerMapper.selectByExample(example);
        if(userList.size()>0) {
            throw new ServiceParamValidateException("数据库中已经存在名为" +oneOwner.getName()+"的用户！");
        }
        int id = ownerMapper.updateByPrimaryKey(oneOwner);
        return ownerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteById(Integer id) {
        Example example = new Example(Owner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id",id);
        List<Owner> userList=ownerMapper.selectByExample(example);
        if(userList.size()==0) {
            throw new ServiceParamValidateException("该数据不存在");
        }
        ownerMapper.deleteByPrimaryKey(id);
    }
}
