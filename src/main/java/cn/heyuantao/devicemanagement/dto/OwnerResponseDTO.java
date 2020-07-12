package cn.heyuantao.devicemanagement.dto;

import cn.heyuantao.devicemanagement.domain.Owner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author he_yu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerResponseDTO implements Serializable {
    private String owner_name;
    private String owner_description;

    public OwnerResponseDTO(Owner oneOwner){
        this.setOwner_name(oneOwner.getName());
        if(oneOwner.getId()==1){
            this.setOwner_description(oneOwner.getDescription()+",这个用户是管理员");
        }else{
            this.setOwner_description(oneOwner.getDescription());
        }
    }
}
