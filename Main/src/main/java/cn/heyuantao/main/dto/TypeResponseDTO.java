package cn.heyuantao.main.dto;

import cn.heyuantao.main.domain.Type;
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
public class TypeResponseDTO implements Serializable {
    private Integer id;
    private String name;
    private String description;

    public TypeResponseDTO(Type oneType){
        BeanUtils.copyProperties(oneType,this);
    }
}
