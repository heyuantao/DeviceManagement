package cn.heyuantao.devicemanagement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * @author he_yu
 */
public class  CustomErrorController implements ErrorController {


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
