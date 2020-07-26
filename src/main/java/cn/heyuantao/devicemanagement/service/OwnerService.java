package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.exception.ResourceNotFoundException;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.OwnerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */
@Service
public class OwnerService  {

    @Resource
    OwnerMapper ownerMapper;


    public List<Owner> getOwners() {
        return ownerMapper.selectAll();
    }

    /**
     * 根据输入的名字查找一个设备所有者的实例，如果不存在则抛出异常
     * @param name
     * @return
     */
    public Owner getOwnerByName(String name){
        Example example = new Example(Owner.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        List<Owner> ownerList = ownerMapper.selectByExample(example);
        if(ownerList.size()!=1){
            throw new ServiceParamValidateException("名字为"+name+"的设备保管人不存在");
        }
        return ownerList.get(0);
    }

    public List<Owner> getOwnersByParams(Map<String,Object> map) {
        return ownerMapper.selectByParams(map);
    }


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


    public Owner getOwnerById(Integer id){
        Owner oneOwner = ownerMapper.selectByPrimaryKey(id);
        if(oneOwner==null){
            throw new ResourceNotFoundException("该设备所有者不存在 !");
        }

        return oneOwner;
    }


    public Owner updateOwnerById(Integer id, Owner ownerData) {
        /*确保该编号存在,否则会抛出异常*/
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


    public void deleteById(Integer id) {
        this.getOwnerById(id);
        ownerMapper.deleteByPrimaryKey(id);
    }
}
