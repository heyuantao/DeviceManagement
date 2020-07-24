/*
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
import java.util.Map;

*/
/**
 * @author he_yu
 *//*

@Service
public class OwnerServiceImpl implements OwnerService {

    @Resource
    OwnerMapper ownerMapper;

    @Override
    public List<Owner> getOwners() {
        return ownerMapper.selectAll();
    }

    @Override
    public List<Owner> getOwnersByParams(Map<String,Object> map) {
        return ownerMapper.selectByParams(map);
    }

    @Override
    public Owner addOwner(Owner oneOwner) {
        Example example = new Example(Owner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",oneOwner.getName());
        List<Owner> userList=ownerMapper.selectByExample(example);
        if(userList.size()>=1){
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
    public Owner updateOwnerById(Integer id, Owner ownerData) {
        */
/*确保该编号存在,否则会抛出异常*//*

        Owner ownerRecord = getOwnerById(id);

        Example example = new Example(Owner.class);
        Example.Criteria criteria = example.createCriteria();

        criteria.andNotEqualTo("id",id);
        criteria.andEqualTo("name",ownerData.getName());
        criteria.andEqualTo("department",ownerData.getDepartment());
        List<Owner> ownerList=ownerMapper.selectByExample(example);
        if(ownerList.size()>=1){
            throw new ServiceParamValidateException("存在重名的数据");
        }

        if(ownerData.getName()!=null){
            ownerRecord.setName(ownerData.getName());
        }
        if(ownerData.getDepartment()!=null){
            ownerRecord.setDepartment(ownerData.getDepartment());
        }
        if(ownerData.getDescription()!=null){
            ownerRecord.setDescription(ownerData.getDescription());
        }
        ownerMapper.updateByPrimaryKey(ownerRecord);
        return ownerRecord;
        //return this.getOwnerById(id);
    }

    @Override
    public void deleteById(Integer id) {
        this.getOwnerById(id);
        ownerMapper.deleteByPrimaryKey(id);
    }
}
*/
