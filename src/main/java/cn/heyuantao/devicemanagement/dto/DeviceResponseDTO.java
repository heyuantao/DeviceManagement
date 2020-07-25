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

    private String location;
    private String type;
    private String owner;

    public DeviceResponseDTO(Device device){
        BeanUtils.copyProperties(device,this);
        location = device.getLocation().getName();
        type = device.getType().getName();
        owner = device.getOwner().getName();
    }
}
