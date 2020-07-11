package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Type;
import cn.heyuantao.devicemanagement.mapper.TypeMapper;
import cn.heyuantao.devicemanagement.service.TypeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author he_yu
 */
public class TypeServiceImpl implements TypeService {
    @Resource
    TypeMapper typeMapper;

    @Override
    public List<Type> getTypes() {
        return null;
    }

    @Override
    public Type addType(Type oneType) {
        return null;
    }
}
