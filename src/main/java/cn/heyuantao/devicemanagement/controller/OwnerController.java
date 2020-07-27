package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.dto.OwnerRequestDTO;
import cn.heyuantao.devicemanagement.dto.OwnerResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.service.OwnerService;
import cn.heyuantao.devicemanagement.util.CustomItemPagination;
import cn.heyuantao.devicemanagement.util.QueryParamsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(tags = {"设备保管人接口"})
@RestController
@RequestMapping("/api/v1/owner")
public class OwnerController {
    @Resource
    OwnerService ownerService;

    @ApiOperation(value = "List the owner !")
    @GetMapping
    public ResponseEntity<CustomItemPagination> list(
            HttpServletRequest request,
            @RequestParam(value="name",defaultValue = "") String name,
            @RequestParam(value="department",defaultValue = "") String department,
            @RequestParam(value="search",defaultValue = "") String search,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "0") Integer pageSize
    ){

        Map<String,Object> params = QueryParamsUtil.getRequestParamMapFromRequestServlet(request);

        PageHelper.startPage(pageNum,pageSize);
        List<Owner> ownerList = ownerService.getOwnersByParams(params);
        PageInfo<Owner> pageInfo = new PageInfo<Owner>(ownerList);

        List<OwnerResponseDTO> responseDTOs= pageInfo.getList().stream().map((item)-> {
            return convertToDTO(item);
        }).collect(Collectors.toList());


        CustomItemPagination customItemPagination=new CustomItemPagination(responseDTOs,pageInfo);

        return new ResponseEntity(customItemPagination,HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<OwnerResponseDTO> create(@Validated @RequestBody OwnerRequestDTO ownerRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        //Owner addOwner = ownerService.addOwner(ownerRequestDTO.convertToDO());
        Owner addOwner = ownerService.addOwner(convertToDO(ownerRequestDTO));
        OwnerResponseDTO ownerResponseDTO = convertToDTO(addOwner);
        return new ResponseEntity(ownerResponseDTO,HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> retrive(@PathVariable("id") Integer id){
        //OwnerResponseDTO responseDTO = new OwnerResponseDTO(ownerService.getOwnerById(id));
        OwnerResponseDTO responseDTO = convertToDTO(ownerService.getOwnerById(id));
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnerResponseDTO> update(@PathVariable("id") Integer id, @Validated @RequestBody OwnerRequestDTO ownerRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }

        //Owner addOwner = ownerService.updateOwnerById(id,ownerRequestDTO.convertToDO());
        Owner addOwner = ownerService.updateOwnerById(id,convertToDO(ownerRequestDTO));
        //OwnerResponseDTO responseDTO = new OwnerResponseDTO(addOwner);
        OwnerResponseDTO responseDTO = convertToDTO(addOwner);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HashMap<String,Object>> destroy(@PathVariable("id") Integer id){
        ownerService.deleteById(id);
        return new ResponseEntity(new HashMap<String,Object>(),HttpStatus.ACCEPTED);
    }

    /**
     * 将接口传过来的数据转变成DO的格式
     * @param ownerRequestDTO
     * @return
     */
    public Owner convertToDO(OwnerRequestDTO ownerRequestDTO){
        Owner oneOwner = new Owner();
        BeanUtils.copyProperties(ownerRequestDTO,oneOwner);
        return oneOwner;
    }

    /**
     * 将数据库中的对象数据转变为DTO的格式
     * @param owner
     * @return
     */
    public OwnerResponseDTO convertToDTO(Owner owner){
        OwnerResponseDTO ownerResponseDTO = new OwnerResponseDTO();
        BeanUtils.copyProperties(owner,ownerResponseDTO);
        return ownerResponseDTO;
    }
}
