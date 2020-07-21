package cn.heyuantao.devicemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotEmpty(message = "用户名不能为空")
    @NotNull(message = "用户名未填写")
    private String username;

    @Length(max = 30, message = "密码过长")
    @NotEmpty(message = "密码不能为空")
    @NotNull(message = "密码未填写")
    private String password;

}
