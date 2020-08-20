package cn.heyuantao.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author he_yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO implements Serializable {
    private Long id;
    private String name;
    private String email;
    private Boolean superuser;
}
