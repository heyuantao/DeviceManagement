package cn.heyuantao.devicemanagement.exception;

/**
 * @author he_yu
 * 该函数用于处理在数据库层面保存出现的问题
 * 当数据入库出现异常时，该异常会被抛出
 */
public class DatabaseValidateException extends RuntimeException{
    public DatabaseValidateException(String message){
        super(message);
    }
}
