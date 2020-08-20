package cn.heyuantao.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

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
    private String locationName;

    @NotEmpty(message = "设备类型不能为空")
    private String typeName;

    @NotEmpty(message = "设备所有人不能为空")
    private String ownerName;

}
