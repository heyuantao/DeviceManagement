package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Location;
import cn.heyuantao.devicemanagement.dto.LocationRequestDTO;
import cn.heyuantao.devicemanagement.dto.LocationResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.service.LocationService;
import cn.heyuantao.devicemanagement.util.QueryParamsUtil;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Api(tags = {"设备存放位置接口"})
@RestController
@RequestMapping("/api/v1/location")
public class LocationController {
    @Resource
    LocationService locationService;

/*    @Autowired
    private HttpServletRequest request;*/

    @GetMapping
    public ResponseEntity<List<LocationResponseDTO>> list(
                        HttpServletRequest request,
                        @RequestParam(value="name",defaultValue = "") String name,
                        @RequestParam(value="department",defaultValue = "") String department,
                        @RequestParam(value="search",defaultValue = "") String search,
                        @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
                        @RequestParam(value="pageSize",defaultValue = "0") Integer pageSize
                        ){

        Map<String,Object> params = QueryParamsUtil.getRequestParamMapFromRequestServlet(request);
        PageHelper.startPage(pageNum,pageSize);

        List<LocationResponseDTO> locationResponseDTOList =locationService.getLocations().stream().
                map((item)->{return new LocationResponseDTO(item);}).collect(Collectors.toList());
        return new ResponseEntity(locationResponseDTOList, HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<LocationResponseDTO> create(@Validated @RequestBody LocationRequestDTO locationRequestDTO,
                                 BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        Location location = locationService.addLocation(locationRequestDTO.convertToDO());
        return new ResponseEntity(location, HttpStatus.ACCEPTED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LocationResponseDTO> retrive(@PathVariable("id") Long id){
        Location location = locationService.getLocationById(id);
        return new ResponseEntity(location, HttpStatus.ACCEPTED);
    }



    @PutMapping("/{id}")
    public ResponseEntity<LocationResponseDTO> update(@PathVariable("id") Long id,
                                    @Validated @RequestBody LocationRequestDTO locationRequestDTO,
                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        Location location = locationService.updateLocationById(id, locationRequestDTO.convertToDO());
        return new ResponseEntity(location,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id){
        locationService.deleteLocationById(id);
        return "";
    }

}
