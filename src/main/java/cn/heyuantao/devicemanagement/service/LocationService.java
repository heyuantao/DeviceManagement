package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Location;
import cn.heyuantao.devicemanagement.exception.ServiceParamValidateException;
import cn.heyuantao.devicemanagement.mapper.LocationMapper;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author he_yu
 */
@Service
public class LocationService{
    @Resource
    LocationMapper locationMapper;


    public List<Location> getLocations() {
        return locationMapper.selectAll();
    }


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


    public Location getLocationById(Integer id) {
        Location location = locationMapper.selectByPrimaryKey(id);
        if(location==null){
            throw new ServiceParamValidateException("该位置不存在");
        }
        return location;
    }


    public Location updateLocationById(Integer id, Location locationData) {
        Location locationRecord = this.getLocationById(id);

        Example example = new Example(Location.class);
        Example.Criteria criteria = example.createCriteria();

        if(locationData.getName()!=null){
            criteria.andNotEqualTo("id",id);
            criteria.andEqualTo("name",locationData.getName());
            if(locationMapper.selectByExample(example).size()>0){
                throw new ServiceParamValidateException("存在同名的位置");
            }
            locationRecord.setName(locationData.getName());
        }

        if(locationData.getDescription()!=null){
            locationRecord.setDescription(locationData.getDescription());
        }

        locationMapper.updateByPrimaryKey(locationRecord);
        return locationRecord;
    }


    public void deleteLocationById(Integer id) {
        Location location = this.getLocationById(id);
        locationMapper.delete(location);
    }
}
