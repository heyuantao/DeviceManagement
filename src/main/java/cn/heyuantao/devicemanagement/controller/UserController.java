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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags={"系统用户接口"})
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    HttpServletRequest httpServletRequest;

    @Resource
    private UserService userService;


    @PreAuthorize("hasRole('ROLE_OTHER')")
    @GetMapping
    public ResponseEntity<CustomItemPagination> list(
            HttpServletRequest request,
            @RequestParam(value="name",defaultValue = "") String name,
            @RequestParam(value="email",defaultValue = "") String email,
            @RequestParam(value="search",defaultValue = "") String search,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "0") Integer pageSize){

        List<GrantedAuthority> authorityList = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        for(GrantedAuthority authority:authorityList){
            System.out.println(authority);
        }

/*        Boolean status = httpServletRequest.isUserInRole("ADMIN");
        System.out.println("Is in the admin role:"+status);*/

        Map<String,Object> params = QueryParamsUtil.getRequestParamMapFromRequestServlet(request);
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList=userService.getUsersByParams(params);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);

        List<UserResponseDTO> userResponseDTOs =userList.stream().map(this::convertToDTO).collect(Collectors.toList());
        CustomItemPagination customItemPagination= new CustomItemPagination(userResponseDTOs,pageInfo);
        return new ResponseEntity(customItemPagination, HttpStatus.ACCEPTED);
    }

    //not use create method

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> retrive(@PathVariable("id") Long id){
        //UserResponseDTO userResponseDTO = new UserResponseDTO(userService.getUsersById(id));
        UserResponseDTO userResponseDTO = convertToDTO(userService.getUsersById(id));
        return new ResponseEntity(userResponseDTO,HttpStatus.ACCEPTED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(
                        @PathVariable("id") Long id,
                        @Validated @RequestBody UserRequestDTO requestDTO,
                        BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        //User user = userService.updateById(id,requestDTO.convertToDO());
        User user = userService.updateById(id,convertToDO(requestDTO));
        //UserResponseDTO responseDTO = new UserResponseDTO(user);
        UserResponseDTO responseDTO = convertToDTO(user);
        return new ResponseEntity(responseDTO,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        userService.deleteById(id);
        return new ResponseEntity("",HttpStatus.ACCEPTED);
    }


    /**
     * 将User的对象转变为UserResponseDTO格式的对象，并准备用户返回数据给前端
     */
    public UserResponseDTO convertToDTO(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        BeanUtils.copyProperties(user,userResponseDTO);
        return userResponseDTO;
    }


    /**
     * 将用户输入的数据转变为User对象
     */
    public User convertToDO(UserRequestDTO userRequestDTO){
        User user = new User();
        BeanUtils.copyProperties(userRequestDTO,user);
        user.setSuperuser(false);
        return user;
    }
}
