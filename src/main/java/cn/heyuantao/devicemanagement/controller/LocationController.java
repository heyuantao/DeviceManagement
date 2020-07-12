package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Location;
import cn.heyuantao.devicemanagement.dto.LocationRequestDTO;
import cn.heyuantao.devicemanagement.dto.LocationResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    @Resource
    LocationService locationService;

    @Autowired
    private HttpServletRequest request;

    @GetMapping
    public ResponseEntity<List<?>> list(){
        List<LocationResponseDTO> locationResponseDTOList =locationService.getLocations().stream().
                map((item)->{return new LocationResponseDTO(item)}).collect(Collectors.toList());
        return new ResponseEntity(locationResponseDTOList, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> add(@Validated @RequestBody LocationRequestDTO, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        
    }

}
