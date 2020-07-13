package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.dto.OwnerRequestDTO;
import cn.heyuantao.devicemanagement.dto.OwnerResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.service.OwnerService;
import cn.heyuantao.devicemanagement.utils.QueryParamsUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/owner/")
public class OwnerController {
    @Resource
    OwnerService ownerService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<?>> list(@RequestParam Map map){
        Map<String,Object> params = QueryParamsUtils.formatRequestParams(map);
        System.out.println(params);
        List<Owner> ownerList = ownerService.getOwnersByParams(params);
        List<OwnerResponseDTO> responseDTOS= ownerList.stream().map((item)-> {
            return new OwnerResponseDTO(item);
        }).collect(Collectors.toList());
        return new ResponseEntity(responseDTOS,HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> add(@Validated @RequestBody OwnerRequestDTO ownerRequestDTO, BindingResult bindingResult){
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
    public String delete(@PathVariable("id") Integer id){
        ownerService.deleteById(id);
        return "";
    }
}
