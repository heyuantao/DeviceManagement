package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.service.UserService;
import cn.heyuantao.devicemanagement.utils.QueryParamsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Resource
    private UserService userService;
    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(value="name",defaultValue = "") String name,
            @RequestParam(value="email",defaultValue = "") String email,
            @RequestParam(value="search",defaultValue = "") String search,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "0") Integer pageSize){

        Map<String,Object> params = QueryParamsUtils.formatRequestParamsFromRequestServlet(httpServletRequest);
        List<User> userList=userService.getUsersByParams(params);
        //tobe continue
        return new ResponseEntity(userList, HttpStatus.ACCEPTED);
    }
}
