package cn.heyuantao.devicemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author he_yu
 */
@Data
@AllArgsConstructor
public class OwnerRequestDTO implements Serializable {
    @JsonProperty("owner_name")
    @NotEmpty(message = "用户名不能为空")
    @Length(max = 10, message = "用户名字过长")
    private String name;

    @JsonProperty("owner_description")
    @Length(max = 10, message = "备注信息过长")
    private String description;
}
