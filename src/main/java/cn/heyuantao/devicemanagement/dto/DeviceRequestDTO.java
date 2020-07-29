package cn.heyuantao.devicemanagement.dto;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.domain.Location;
import cn.heyuantao.devicemanagement.domain.Type;
import cn.heyuantao.devicemanagement.mapper.LocationMapper;
import cn.heyuantao.devicemanagement.mapper.OwnerMapper;
import cn.heyuantao.devicemanagement.mapper.TypeMapper;
import cn.heyuantao.devicemanagement.service.LocationService;
import cn.heyuantao.devicemanagement.service.OwnerService;
import cn.heyuantao.devicemanagement.service.TypeService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Null;
import java.util.Date;

/**接受设备的信息，并将设备添加到数据库中
 * @author he_yu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequestDTO {

    @NotEmpty(message = "设备名字不能为空")
    @Length(max = 100, message = "设备名字过长")
    private String name;

    @NotEmpty(message = "设备厂商名字不能为空")
    @Length(max = 100, message = "设备厂商名字过长")
    private String vendor;

    @NotEmpty(message = "设备序列号不能为空")
    @Length(max = 100, message = "设备名字过长")
    private String sn;

    @NotEmpty(message = "设备资产编号号不能为空")
    @Length(max = 100, message = "设备资产编号过长")
    private String assetNo;


    @NotEmpty(message = "设备存放位置不能为空")
    private String location_name;

    @NotEmpty(message = "设备类型不能为空")
    private String type_name;

    @NotEmpty(message = "设备所有人不能为空")
    private String owner_name;

/*    public Device convertToDTO(){
        LocationService locationService = new LocationService();
        OwnerService ownerService = new OwnerService();
        TypeService typeService = new TypeService();

        Device device = new Device();
        BeanUtils.copyProperties(this,device);

        device.setOwner(ownerService.getOwnerByName(owner__name));
        device.setType(typeService.getTypeByName(type__name));
        device.setLocation(locationService.getLocationByName(location__name));


        return device;
    }*/
}
