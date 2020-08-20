package cn.heyuantao.main.dto;

import cn.heyuantao.main.domain.Type;
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
public class TypeRequestDTO implements Serializable {
    @NotEmpty(message = "类型不能为空")
    @Length(max = 10, message = "类型名字过长")
    private String name;

    @Length(max = 10, message = "类型信息过长")
    private String description;

    public Type convertToDO(){
        Type oneType = new Type();
        BeanUtils.copyProperties(this,oneType);
        return oneType;
    }
}
