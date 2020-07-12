package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Location;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.LocationMapper;
import cn.heyuantao.devicemanagement.service.LocationService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Resource
    LocationMapper locationMapper;

    @Override
    public List<Location> getLocations() {
        return locationMapper.selectAll();
    }

    @Override
    public Location addLocation(Location location) {
        Example example=new Example(Location.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",location.getName());
        if(locationMapper.selectByExample(example).size()>0){
            throw new ServiceParamValidateException("存在同名的位置");
        }
        locationMapper.insert(location);
        return location;
    }

    @Override
    public Location getLocationById(Integer id) {
        Location location = locationMapper.selectByPrimaryKey(id);
        if(location==null){
            throw new ServiceParamValidateException("该位置不存在");
        }
        return location;
    }

    @Override
    public Location updateLocationById(Integer id, Location location) {
        Location locationRecord = this.getLocationById(id);

        Example example = new Example(Location.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("id",id);
        criteria.andEqualTo("name",location.getName());
        if(locationMapper.selectByExample(example).size()>0){
            throw new ServiceParamValidateException("存在同名的位置");
        }

        location.setId(id);
        locationMapper.updateByPrimaryKey(location);
        return location;
    }

    @Override
    public void deleteLocationById(Integer id) {
        Location location = this.getLocationById(id);
        locationMapper.delete(location);
    }
}
