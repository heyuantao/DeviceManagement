package cn.heyuantao.devicemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author he_yu
 */

@Data
@AllArgsConstructor
public class ErrorDetails {
    //private Date timestamp;

    private String message;
    private String details;
}
