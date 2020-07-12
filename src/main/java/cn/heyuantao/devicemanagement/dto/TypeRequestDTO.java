package cn.heyuantao.devicemanagement.dto;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.domain.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author he_yu
 */

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
