package cn.heyuantao.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import java.io.Serializable;

/**
 * @author he_yu
 * 该数据结构仅仅用户修改用户的邮箱和密码，因此不包含"用户名"和"是否为超级用户"字段
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO implements Serializable {

    @Length(max = 30, message = "邮箱名字过长")
    @Email
    private String email;

    @Length(max = 30, message = "密码过长")
    private String password;

}
