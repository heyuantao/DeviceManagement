package cn.heyuantao.main.service;

import cn.heyuantao.main.domain.Location;
import cn.heyuantao.main.exception.ServiceParamValidateException;
import cn.heyuantao.main.mapper.LocationMapper;
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

    /**
     * 通过查找名字来返回一个地址实例，如果不存在则抛异常
     * @param name
     * @return
     */
    public Location getLocationByName(String name){
        Example example = new Example(Location.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name);
        List<Location> locationList = locationMapper.selectByExample(example);
        if(locationList.size()!=1){
            throw new ServiceParamValidateException("名字为"+name+"的地址不存在");
        }
        return locationList.get(0);
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


    public Location getLocationById(Long id) {
        Location location = locationMapper.selectByPrimaryKey(id);
        if(location==null){
            throw new ServiceParamValidateException("该位置不存在");
        }
        return location;
    }


    public Location updateLocationById(Long id, Location locationData) {
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

    /**
     * 通过ID来删除某个设备
     * @param id
     */
    public void deleteLocationById(Long id) {
        Location location = this.getLocationById(id);
        locationMapper.delete(location);
    }
}
