package cn.heyuantao.devicemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**软件版本和说明信息
 * @author he_yu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareInformation implements Serializable {
    private String version;
    private String title;
    private String description;
}