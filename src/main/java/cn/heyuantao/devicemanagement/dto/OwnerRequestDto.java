package cn.heyuantao.devicemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author he_yu
 */
@Data
@AllArgsConstructor
public class OwnerRequestDto implements Serializable {
    @JsonProperty("owner_name")
    private String name;
    @JsonProperty("owner_description")
    private String description;
    
}
