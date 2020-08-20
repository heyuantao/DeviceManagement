package cn.heyuantao.main.dto;

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
    private Integer id;
    private String name;
    private String department;
    private String description;
}
