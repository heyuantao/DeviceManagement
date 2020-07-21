package cn.heyuantao.devicemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author he_yu
 * 对API的login登录时使用的数据
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequestDTO implements Serializable {
    @Length(max = 30, message = "名字过长")
    @NotEmpty(message = "名字不能为空")
    private String username;

    @Length(max = 30, message = "密码过长")
    @NotEmpty(message = "密码不能为空")
    private String password;

}
