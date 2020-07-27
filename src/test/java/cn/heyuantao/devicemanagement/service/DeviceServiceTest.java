package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.domain.Location;
import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.domain.Type;
import cn.heyuantao.devicemanagement.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class DeviceServiceTest {
    @Resource
    DeviceService deviceService;
    @Resource
    TypeService typeService;
    @Resource
    LocationService locationService;
    @Resource
    OwnerService ownerService;

    @Test
    void getDevices() {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Device> deviceList = deviceService.selectDevicesByParams(map);
        for(Device oneDevice:deviceList){
            System.out.println(oneDevice);
        }
    }

    @Test
    void testAddDeviceWithForgienKey(){
        Type type = typeService.getTypeByName("无人机");
        Location location = locationService.getLocationByName("08A502");
        Owner owner = ownerService.getOwnerByName("张三");
        System.out.println(type+"\n"+location+"\n"+owner);

    }
}