package cn.heyuantao.devicemanagement.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.tomcat.util.http.fileupload.MultipartStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author he_yu
 * 生成验证码的接口，底层依赖于Google的Kaptcha
 */
@RestController
@RequestMapping("/api/v1/captcha")
public class CaptchaController {
    @Autowired
    DefaultKaptcha defaultKaptcha;

    @GetMapping
    public void getCatpcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)throws Exception{
        byte[] captchaJpg = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            String code = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("captcha",code);
            BufferedImage bufferedImage = defaultKaptcha.createImage(code);
            ImageIO.write(bufferedImage,"jpg",byteArrayOutputStream);
        }catch (IllegalArgumentException e){
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        captchaJpg = byteArrayOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaJpg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }
}
