package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.dto.DeviceRequestDTO;
import cn.heyuantao.devicemanagement.dto.DeviceResponseDTO;
import cn.heyuantao.devicemanagement.dto.TypeResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.mapper.DeviceMapper;
import cn.heyuantao.devicemanagement.service.DeviceService;
import cn.heyuantao.devicemanagement.util.CustomItemPagination;
import cn.heyuantao.devicemanagement.util.HttpRequestQueryParams;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**管理设备的增删改查
 * @author he_yu
 */
@Api(tags={"设备管理接口"})
@RestController
@RequestMapping("/api/v1/devices")
public class DeviceController {
    @Resource
    DeviceService deviceService;


    @ApiOperation(value = "查找所有的设备")
    @GetMapping
    public ResponseEntity<CustomItemPagination> list(
            HttpServletRequest httpServletRequest,
            @RequestParam(value="name",defaultValue = "") String name,
            @RequestParam(value="sn",defaultValue = "") String sn,
            @RequestParam(value="asset_no",defaultValue = "") String asset_no,
            @RequestParam(value="search",defaultValue = "") String search,
            @RequestParam(value="pageNum",defaultValue = "1") Integer pageNum,
            @RequestParam(value="pageSize",defaultValue = "0") Integer pageSize
    ){

        HttpRequestQueryParams params = new HttpRequestQueryParams(httpServletRequest);
        PageHelper.startPage(pageNum,pageSize);
        List<Device> deviceList = null;
        PageInfo<Device> pageInfo = null;

        if(params.hasFilterParams()){
            deviceList = deviceService.filterDeviceByParams(params.getFilterString());
            pageInfo = new PageInfo<Device>(deviceList);
        }else{
            deviceList = deviceService.selectDevicesByParams(params.getSelectMap());
            pageInfo = new PageInfo<Device>(deviceList);
        }


        List<DeviceResponseDTO> responseDTOs= pageInfo.getList().stream().map((item)-> {return new DeviceResponseDTO(item);}).collect(Collectors.toList());
        CustomItemPagination customItemPagination = new CustomItemPagination(responseDTOs,pageInfo);
        return new ResponseEntity(customItemPagination,HttpStatus.ACCEPTED);
    }

    @ApiOperation(value = "创建一个新的设备")
    @PostMapping
    public ResponseEntity<DeviceRequestDTO> create(
            @Validated @RequestBody DeviceRequestDTO deviceRequestDTO,
            BindingResult bindingResult
    ){
        if(bindingResult.hasErrors()){
            throw new RequestParamValidateException(bindingResult);
        }
        Device device = deviceRequestDTO.convertToDTO();
        deviceService.addDevice(device);
        DeviceResponseDTO deviceResponseDTO = new DeviceResponseDTO(device);
        return new ResponseEntity(deviceResponseDTO, HttpStatus.ACCEPTED);
    }
}
