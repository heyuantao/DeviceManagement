package cn.heyuantao.devicemanagement.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**当访问接口出现问题（权限，异常）时，接口会返回该格式的数据
 * 该类型的数据以JSON的方式返回，状态码单独在HTTP协议中返回，状态码由具体的接口来控制
 *
 * @author he_yu
 */
@Data
@AllArgsConstructor
public class ErrorDetails {
    private String message;
    private String details;

}
