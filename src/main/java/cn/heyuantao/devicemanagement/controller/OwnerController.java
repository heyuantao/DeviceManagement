package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.dto.OwnerRequestDTO;
import cn.heyuantao.devicemanagement.dto.OwnerResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.service.OwnerService;
import cn.heyuantao.devicemanagement.utils.CustomItemPagination;
import cn.heyuantao.devicemanagement.utils.QueryParamsUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {
    @Resource
    OwnerService ownerService;

    @Autowired
    private HttpServletRequest request;


    @ApiOperation(value = "List the owner !")
    @GetMapping
    public ResponseEntity<List<?>> list(@RequestParam(required = false) Map<String,String> map, //HttpServletRequest request
                                        @RequestParam(value="search",defaultValue = "") String  search,
                                        @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                                        @RequestParam(value="pageSize",defaultValue = "0") Integer pageSize
                                        ){

        //Map rawParams = (Map<String,String>)request.getParameterMap();
        Map<String,Object> params = QueryParamsUtils.formatRequestParams(map);
/*        System.out.println("#############");
        System.out.println(rawParams);
        System.out.println(params);
        System.out.println("#############");*/
        PageHelper.startPage(pageNum,pageSize);
        List<Owner> ownerList = ownerService.getOwnersByParams(params);
        PageInfo<Owner> pageInfo = new PageInfo<Owner>(ownerList);

        List<OwnerResponseDTO> responseDTOs= pageInfo.getList().stream().map((item)-> {
            return new OwnerResponseDTO(item);
        }).collect(Collectors.toList());

        CustomItemPagination customItemPagination=new CustomItemPagination(responseDTOs,pageInfo);

        return new ResponseEntity(customItemPagination.getPaginatedData(),HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody OwnerRequestDTO ownerRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        Owner addOwner = ownerService.addOwner(ownerRequestDTO.convertToDO());
        return new ResponseEntity(new OwnerResponseDTO(addOwner),HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrive(@PathVariable("id") Integer id){
        OwnerResponseDTO responseDTO = new OwnerResponseDTO(ownerService.getOwnerById(id));
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @Validated @RequestBody OwnerRequestDTO ownerRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        Owner addOwner = ownerService.updateOwnerById(id,ownerRequestDTO.convertToDO());
        OwnerResponseDTO responseDTO = new OwnerResponseDTO(addOwner);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public String destroy(@PathVariable("id") Integer id){
        ownerService.deleteById(id);
        return "";
    }
}
