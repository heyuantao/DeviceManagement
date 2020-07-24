/*
package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.auth.CustomUserDetails;
import cn.heyuantao.devicemanagement.config.SoftwareInformation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.service.ApiInfo;

import java.util.HashMap;
import java.util.Map;

*/
/**
 * @author he_yu
 * 首页信息
 *//*

@Api(tags={"软件基本信息"})
@Controller
@RequestMapping("/")
public class IndexPageController {

    @Autowired
    SoftwareInformation softwareInformation;

    @ApiIgnore
    @GetMapping
    public String index(Model model){
        return "index";
    }


    @GetMapping("/version")
    public ResponseEntity<SoftwareInformation> version(){
        return new ResponseEntity(softwareInformation,HttpStatus.ACCEPTED);
    }
}
*/
