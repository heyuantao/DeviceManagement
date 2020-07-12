package cn.heyuantao.devicemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author he_yu
 */
@Data
@AllArgsConstructor
public class OwnerRequestDto implements Serializable {
    private String owner_name;
    private String owner_description;
}
