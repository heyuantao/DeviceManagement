package cn.heyuantao.devicemanagement.dto;

import cn.heyuantao.devicemanagement.domain.Owner;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @author he_yu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDTO implements Serializable {
    @JsonProperty("owner_id")
    private Integer id;
    @JsonProperty("owner_name")
    private String name;
    @JsonProperty("owner_description")
    private String description;
    @JsonProperty("owner_others")
    private String other;

    public OwnerResponseDTO(Owner oneOwner){
        BeanUtils.copyProperties(oneOwner,this);
        if(oneOwner.getId()==1){
            this.setOther("该用户为管理员");
        }else{
            this.setOther("该用户为普通用户");
        }
    }

}
