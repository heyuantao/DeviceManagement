package cn.heyuantao.devicemanagement.domain;

import cn.heyuantao.devicemanagement.service.LocationService;
import cn.heyuantao.devicemanagement.service.OwnerService;
import cn.heyuantao.devicemanagement.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.annotation.Resource;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


/**
 * @author he_yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Device {
/*    @Resource
    LocationService locationService;
    @Resource
    TypeService typeService;
    @Resource
    OwnerService ownerService;*/


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String vendor;
    private String sn;
    private String assetNo;
    private Date inDate;
    private Date updated;

    private Long location_id;
    private Long type_id;
    private Long owner_id;

/*    public Location getLocation(){
        return locationService.getLocationById(location_id);
    }

    public Type getType(){
        return typeService.getTypeById(type_id);
    }

    public Owner getOwner(){
        return ownerService.getOwnerById(owner_id);
    }*/
}
