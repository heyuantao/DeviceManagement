package cn.heyuantao.devicemanagement.dto;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.domain.Location;
import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.domain.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**返回设备的详细信息
 * 在返回的过程中将设备信息的分层对象结构转变为平面结构
 * @author he_yu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeviceResponseDTO {
    private Long id;
    private String name;
    private String vendor;
    private String sn;
    private String assetNo;
    private Date inDate;
    private Date updated;

    private String location__name;
    private String type__name;
    private String owner__name;
    private String owner__department;

    public DeviceResponseDTO(Device device){
        BeanUtils.copyProperties(device,this);
        location__name = device.getLocation().getName();
        type__name = device.getType().getName();
        owner__name = device.getOwner().getName();
        owner__department = device.getOwner().getDepartment();
    }
}
