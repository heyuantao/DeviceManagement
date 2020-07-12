package cn.heyuantao.devicemanagement.exception;


/**
 * @author he_yu
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message){
        super(message);
    }
}
