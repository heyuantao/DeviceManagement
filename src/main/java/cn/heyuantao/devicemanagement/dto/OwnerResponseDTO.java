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
    private Integer id;
    private String name;
    private String department;
    private String description;
}
