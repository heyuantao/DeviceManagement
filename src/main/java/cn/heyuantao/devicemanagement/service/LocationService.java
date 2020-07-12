package cn.heyuantao.devicemanagement.service;


import cn.heyuantao.devicemanagement.domain.Location;

import java.util.List;

/**
 * @author he_yu
 */
public interface LocationService {
    List<Location> getLocations();
    Location addLocation(Location oneLocation);

    //details
    Location getLocationById(Integer id);
    Location updateLocationById(Integer id, Location location);
    void deleteLocationById(Integer id);
}
