package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Type;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.TypeMapper;
import cn.heyuantao.devicemanagement.service.TypeService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author he_yu
 */

@Service
public class TypeServiceImpl implements TypeService {
    @Resource
    TypeMapper typeMapper;

    @Override
    public List<Type> getTypes() {
        return typeMapper.selectAll();
    }

    @Override
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

    @Override
    public Type getTypeById(Integer id) {
        Type oneType = typeMapper.selectByPrimaryKey(id);
        if(oneType==null){
            throw new ServiceParamValidateException("该类型数据不存在");
        }
        return oneType;
    }

    @Override
    public Type updateTypeById(Integer id, Type newType) {
        Example example = new Example(Type.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",newType.getName());
        criteria.andNotEqualTo("id",id);
        if(typeMapper.selectByExample(example).size()>0){
            throw new ServiceParamValidateException(("存在同名数据"));
        }

        Type typeRecord = this.getTypeById(id);
        newType.setId(typeRecord.getId());
        typeMapper.updateByPrimaryKey(newType);
        return newType;
    }

    @Override
    public void deleteById(Integer id) {
        Type typeRecord = this.getTypeById(id);
        typeMapper.delete(typeRecord);
    }

}
