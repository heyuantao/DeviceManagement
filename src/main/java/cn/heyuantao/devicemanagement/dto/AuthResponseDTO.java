package cn.heyuantao.devicemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author he_yu
 * 该类用于返回用户的Token（JWT）
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO implements Serializable {
    private String jwt;
}
