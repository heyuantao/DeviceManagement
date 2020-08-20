package cn.heyuantao.main.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    /**
     * runStatus is dev or prod
     */
    private String runStatus;
}