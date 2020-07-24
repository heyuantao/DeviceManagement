package cn.heyuantao.devicemanagement.dto;

import cn.heyuantao.devicemanagement.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author he_yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private Boolean superuser;

    public UserResponseDTO(User user){
        BeanUtils.copyProperties(user,this);
    }
}
