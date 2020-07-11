package cn.heyuantao.devicemanagement.service;


import cn.heyuantao.devicemanagement.domain.Location;

import java.util.List;

/**
 * @author he_yu
 */
public interface LocationService {
    public List<Location> getLocations();
    public Location addLocation(Location oneLocation);
}
