package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Location;
import cn.heyuantao.devicemanagement.mapper.LocationMapper;
import cn.heyuantao.devicemanagement.service.LocationService;

import javax.annotation.Resource;
import java.util.List;

public class LocationServiceImpl implements LocationService {
    @Resource
    LocationMapper locationMapper;

    @Override
    public List<Location> getLocations() {
        return locationMapper.selectAll();
    }

    @Override
    public Location addLocation(Location oneLocation) {
        locationMapper.insert(oneLocation);
        return oneLocation;
    }
}
