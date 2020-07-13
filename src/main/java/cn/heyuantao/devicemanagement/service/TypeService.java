package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Type;

import java.util.List;
import java.util.Map;

public interface TypeService {
    List<Type> getTypes();
    List<Type> getTypesByParams(Map<String, Object> params);
    Type addType(Type oneType);

    Type getTypeById(Integer id);
    Type updateTypeById(Integer id, Type type);
    void deleteById(Integer id);

}
