package cn.heyuantao.devicemanagement.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "该接口用于生成验证码图片")
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

    @ApiOperation(value = "该接口用检查验证码")
    @PostMapping("/check")
    public ResponseEntity<?> checkCaptcha(
            @RequestParam(value = "captcha") String verificationCode,
            HttpServletRequest httpServletRequest) {

        String code = (String) httpServletRequest.getSession().getAttribute("captcha");
        httpServletRequest.getSession().removeAttribute("verificationCode");
        if (StringUtils.isEmpty(code) || !code.equals(verificationCode)) {
            return new ResponseEntity("验证码错误，或已失效", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity("验证通过", HttpStatus.ACCEPTED);
    }
}
