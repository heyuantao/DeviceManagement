package cn.heyuantao.devicemanagement.controller;

import org.springframework.boot.web.servlet.error.ErrorController;

/**
 * @author he_yu
 */
public class  CustomErrorController implements ErrorController {
    @Override
    public String getErrorPath() {
        return "/error";
    }
}
