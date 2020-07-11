package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Type;

import java.util.List;

public interface TypeService {
    List<Type> getTypes();
    Type addType(Type oneType);
}
