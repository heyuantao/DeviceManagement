package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.auth.CustomUserDetails;
import cn.heyuantao.devicemanagement.config.SoftwareInformation;
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

/**
 * @author he_yu
 * 首页信息
 */
@ApiIgnore
@Controller
@RequestMapping("/")
public class IndexPageController {

    @Autowired
    SoftwareInformation softwareInformation;

    @GetMapping
    public String index(Model model){
        return "index";
    }


    @GetMapping("/version")
    public ResponseEntity<Map> version(){
        Map versionInformationMap = new HashMap<String,String>();
        versionInformationMap.put("version",softwareInformation.getVersion());
        versionInformationMap.put("title",softwareInformation.getTitle());
        versionInformationMap.put("description",softwareInformation.getDescription());
        return new ResponseEntity(versionInformationMap,HttpStatus.ACCEPTED);
    }
}
