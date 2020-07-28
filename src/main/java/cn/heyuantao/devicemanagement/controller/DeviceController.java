package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.dto.DeviceRequestDTO;
import cn.heyuantao.devicemanagement.dto.DeviceResponseDTO;
import cn.heyuantao.devicemanagement.dto.TypeResponseDTO;
import cn.heyuantao.devicemanagement.exception.RequestParamValidateException;
import cn.heyuantao.devicemanagement.mapper.DeviceMapper;
import cn.heyuantao.devicemanagement.service.DeviceService;
import cn.heyuantao.devicemanagement.service.LocationService;
import cn.heyuantao.devicemanagement.service.OwnerService;
import cn.heyuantao.devicemanagement.service.TypeService;
import cn.heyuantao.devicemanagement.util.CustomItemPagination;
import cn.heyuantao.devicemanagement.util.HttpRequestQueryParams;
import cn.heyuantao.devicemanagement.util.QueryParamsUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
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

    @Resource
    DeviceMapper deviceMapper;

    @Resource
    LocationService locationService;

    @Resource
    TypeService typeService;

    @Resource
    OwnerService ownerService;


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


        //List<DeviceResponseDTO> responseDTOs= pageInfo.getList().stream().map((item)-> {return new DeviceResponseDTO(item);}).collect(Collectors.toList());
        List<DeviceResponseDTO> responseDTOs= pageInfo.getList().stream().map((item)-> {return convertToDTO(item);}).collect(Collectors.toList());

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
        Device device = convertToDO(deviceRequestDTO);
        Device deviceRecord = deviceService.addDevice(device);

        //DeviceResponseDTO deviceResponseDTO = new DeviceResponseDTO(device);
        DeviceResponseDTO deviceResponseDTO = convertToDTO(deviceRecord);
        return new ResponseEntity(deviceResponseDTO, HttpStatus.ACCEPTED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> retrive(@PathVariable("id") Long id){
        Device device = deviceService.getDeviceById(id);
        return new ResponseEntity(convertToDTO(device),HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> update(
            @PathVariable("id") Long id,
            @Validated @RequestBody DeviceRequestDTO deviceRequestDTO,
            BindingResult bindingResult
    ){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map> delete(@PathVariable("id") Long id){
        
        return new ResponseEntity(new HashMap<String,Object>(1),HttpStatus.ACCEPTED);
    }

    /**
     * 将请求的数据转换为对应的Device对象
     * @param deviceRequestDTO
     * @return
     */
    public Device convertToDO(DeviceRequestDTO deviceRequestDTO){

        Device device = new Device();
        BeanUtils.copyProperties(deviceRequestDTO,device);

        device.setUpdated(new Date(System.currentTimeMillis()));
        device.setInDate(new Date(System.currentTimeMillis()));

        device.setOwner(ownerService.getOwnerByName(deviceRequestDTO.getOwner__name()));
        device.setType(typeService.getTypeByName(deviceRequestDTO.getType__name()));
        device.setLocation(locationService.getLocationByName(deviceRequestDTO.getLocation__name()));

        return device;
    }

    /**
     * 将设备实体转换为用户可以看的数据并返回
     * @param device
     * @return
     */
    public DeviceResponseDTO convertToDTO(Device device){
        DeviceResponseDTO deviceResponseDTO = new DeviceResponseDTO();

        BeanUtils.copyProperties(device,deviceResponseDTO);
        deviceResponseDTO.setLocation__name(device.getLocation().getName());
        deviceResponseDTO.setType__name(device.getType().getName());
        deviceResponseDTO.setOwner__name(device.getOwner().getName());
        deviceResponseDTO.setOwner__department(device.getOwner().getDepartment());
        return deviceResponseDTO;
    }
}
