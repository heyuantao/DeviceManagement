/*
package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.dto.UserRequestDTO;
import cn.heyuantao.devicemanagement.dto.UserResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.service.UserService;
import cn.heyuantao.devicemanagement.util.CustomItemPagination;
import cn.heyuantao.devicemanagement.util.QueryParamsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags={"系统用户接口"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping
    public ResponseEntity<CustomItemPagination> list(
            HttpServletRequest request,
            @RequestParam(value="name",defaultValue = "") String name,
            @RequestParam(value="email",defaultValue = "") String email,
            @RequestParam(value="search",defaultValue = "") String search,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "0") Integer pageSize){

        Map<String,Object> params = QueryParamsUtil.getRequestParamMapFromRequestServlet(request);
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList=userService.getUsersByParams(params);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);

        List<UserResponseDTO> userResponseDTOs =userList.stream().map((item)->{return new UserResponseDTO(item);}).collect(Collectors.toList());
        CustomItemPagination customItemPagination= new CustomItemPagination(userResponseDTOs,pageInfo);
        return new ResponseEntity(customItemPagination, HttpStatus.ACCEPTED);
    }

    //not use create method

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> retrive(@PathVariable("id") Integer id){
        UserResponseDTO userResponseDTO = new UserResponseDTO(userService.getUsersById(id));
        return new ResponseEntity(userResponseDTO,HttpStatus.ACCEPTED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
                        @PathVariable("id") Integer id,
                        @Validated @RequestBody UserRequestDTO requestDTO,
                        BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        User user = userService.updateById(id,requestDTO.convertToDO());
        UserResponseDTO responseDTO = new UserResponseDTO(user);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){
        userService.deleteById(id);
        return new ResponseEntity("",HttpStatus.ACCEPTED);
    }
}
*/
