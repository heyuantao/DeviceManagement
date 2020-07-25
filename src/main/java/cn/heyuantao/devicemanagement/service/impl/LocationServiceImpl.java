package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.entity.Location;
import cn.heyuantao.devicemanagement.exception.DatabaseValidateException;
import cn.heyuantao.devicemanagement.exception.ServiceValidateException;
import cn.heyuantao.devicemanagement.repository.LocationRepository;
import cn.heyuantao.devicemanagement.service.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


/**
 * @author he_yu
 */
@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public List<Location> getLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location addLocation(Location location) {

        if(locationRepository.findLocationByName(location.getName())!=null){
            throw new ServiceValidateException("存在同名的位置");
        }

        try{
            locationRepository.save(location);
        }catch (Exception ex){
            log.error(ex.getMessage());
            throw new DatabaseValidateException(ex.getMessage());
        }

        return location;
    }

    @Override
    public Location getLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);

        if(!location.isPresent()){
            throw new ServiceValidateException("该位置不存在");
        }

        return location.get();
    }

    @Override
    public Location updateLocationById(Long id, Location locationData) {
        Optional<Location> locationRecord = locationRepository.findById(id);
        if(!locationRecord.isPresent()){
            throw new ServiceValidateException("该位置不存在");
        }

        Location savedLocation = null;
        try{
            locationData.setId(locationRecord.get().getId());
            savedLocation = locationRepository.save(locationData);
        }catch (Exception ex){
            throw new DatabaseValidateException(ex.getMessage());
        }
        return savedLocation;
    }

    @Override
    public void deleteLocationById(Long id) {
        Optional<Location> location = locationRepository.findById(id);
        if(!location.isPresent()){
            throw new ServiceValidateException("该位置不存在");
        }
        locationRepository.deleteById(id);
    }
}
