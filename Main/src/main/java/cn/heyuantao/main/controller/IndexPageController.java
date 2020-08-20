package cn.heyuantao.main.controller;

import cn.heyuantao.main.domain.SoftwareInformation;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author he_yu
 * 首页信息
 */
@Api(tags={"软件基本信息"})
@Controller
@RequestMapping("/")
public class IndexPageController {

    @Resource
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
