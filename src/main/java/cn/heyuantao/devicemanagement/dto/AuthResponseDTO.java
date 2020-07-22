package cn.heyuantao.devicemanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @author he_yu
 * 该类用于返回用户的Token（JWT）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO implements Serializable {
    private String jwt;

    @JsonProperty("jwt_with_bearer")
    private String jwtWithBearer;

    public AuthResponseDTO(String jwt) {
        this.jwt = jwt;
        List<String> stringList = new ArrayList<String>();
        stringList.add("Bearer");
        stringList.add(jwt);
        this.jwtWithBearer = StringUtils.join(stringList," ");
    }

}
