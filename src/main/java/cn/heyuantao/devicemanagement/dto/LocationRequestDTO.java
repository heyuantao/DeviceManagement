package cn.heyuantao.devicemanagement.dto;

import cn.heyuantao.devicemanagement.entity.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author he_yu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequestDTO implements Serializable {
    @NotEmpty(message = "位置名字能为空")
    @Length(max = 10, message = "位置名字过长")
    private String name;

    @Length(max = 20, message = "位置描述信息过长")
    private String description;

    public Location convertToDO(){
        Location location=new Location();
        BeanUtils.copyProperties(location,this);
        return location;
    }
}
