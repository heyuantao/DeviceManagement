package cn.heyuantao.main.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author he_yu
 */

@Data
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private String details;

}
