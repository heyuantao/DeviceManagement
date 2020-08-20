package cn.heyuantao.main.exception;

/**
 * @author he_yu
 * 该函数用于处理在服务层面的异常问题
 * 当服务层数据因为完整性或权限出现异常时，可以被抛出并被全局异常处理层面进行处理
 */
public class ServiceParamValidateException extends RuntimeException{
    public ServiceParamValidateException(String message){
        super(message);
    }
}
