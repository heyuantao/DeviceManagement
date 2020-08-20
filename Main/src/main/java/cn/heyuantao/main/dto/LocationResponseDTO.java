package cn.heyuantao.main.dto;

import cn.heyuantao.main.domain.Location;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author he_yu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponseDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;

    public LocationResponseDTO(Location location){
        BeanUtils.copyProperties(location,this);
    }
}
