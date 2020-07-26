package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Type;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.TypeMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author he_yu
 */

@Service
public class TypeService{
    @Resource
    TypeMapper typeMapper;


    public List<Type> getTypes() {
        return typeMapper.selectAll();
    }

    /**
     * 根据输入的类型名字返回一个类型的实例，如果不存在则抛出异常
     * @param name
     * @return
     */
    public Type getTypeByName(String name){
        Example example = new Example(Type.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        List<Type> typeList = typeMapper.selectByExample(example);
        if(typeList.size()!=1){
            throw new ServiceParamValidateException("名字为"+name+"的类型不存在");
        }
        return typeList.get(0);
    }

    public List<Type> getTypesByParams(Map<String, Object> params) {
        return typeMapper.selectByParams(params);
    }


    public Type addType(Type oneType) {
        Example example = new Example(Type.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",oneType.getName());
        List<Type> typeList=typeMapper.selectByExample(example);
        if(typeList.size()>0){
            throw new ServiceParamValidateException("存在同名的类型");
        }
        typeMapper.insert(oneType);
        return oneType;
    }


    public Type getTypeById(Integer id) {
        Type oneType = typeMapper.selectByPrimaryKey(id);
        if(oneType==null){
            throw new ServiceParamValidateException("该类型数据不存在");
        }
        return oneType;
    }


    public Type updateTypeById(Integer id, Type typeData) {
        Type typeRecord = this.getTypeById(id);

        Example example = new Example(Type.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",typeData.getName());
        criteria.andNotEqualTo("id",id);
        if(typeMapper.selectByExample(example).size()>0){
            throw new ServiceParamValidateException(("存在同名数据"));
        }

        if(typeData.getName()!=null){
            typeRecord.setName(typeData.getName());
        }
        if(typeData.getDescription()!=null){
            typeRecord.setDescription(typeData.getDescription());
        }

        typeMapper.updateByPrimaryKey(typeRecord);
        return typeRecord;
    }


    public void deleteById(Integer id) {
        Type typeRecord = this.getTypeById(id);
        typeMapper.delete(typeRecord);
    }

}
