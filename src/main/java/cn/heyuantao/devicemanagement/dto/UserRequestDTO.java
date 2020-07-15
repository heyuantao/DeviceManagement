package cn.heyuantao.devicemanagement.dto;

import cn.heyuantao.devicemanagement.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author he_yu
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDTO implements Serializable {
/*    @NotEmpty(message = "类型不能为空")
    @Length(max = 10, message = "类型名字过长")
    private String name;*/

    @Length(max = 30, message = "邮箱名字过长")
    @Email
    private String email;

    @Length(max = 30, message = "密码过长")
    private String password;

    public User convertToDO(){
        User user = new User();
        BeanUtils.copyProperties(this,user);
        user.setSuperuser(false);
        return user;
    }
}
