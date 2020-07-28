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
import java.lang.reflect.Field;
import java.util.Date;
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
    private boolean getAssetNo;

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

        try{

            Type type = typeService.getTypeByName("无人机");
            Location location = locationService.getLocationByName("08A502");
            Owner owner = ownerService.getOwnerByName("张三");
            System.out.println("Add new Device !");
            System.out.println(type+"\n"+location+"\n"+owner);
            Device device = new Device();
            device.setName("Lenovo笔记本电脑");
            device.setVendor("Lenovo");
            device.setSn("11111111111");
            device.setAssetNo("xxxxxxxxxxx");
            device.setInDate(new Date(System.currentTimeMillis()));
            device.setUpdated(new Date(System.currentTimeMillis()));
            device.setLocation(location);
            device.setType(type);
            device.setOwner(owner);

            deviceService.addDevice(device);

            System.out.println("添加新的设备记录 !");
            System.out.println(device);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void testUpdateDevice() {
        Map<String,Object> map = new HashMap<>();
        map.put("asset_no","xxxxxxxxxxx");
        List<Device> deviceList= deviceService.selectDevicesByParams(map);

        Device theDeviceTobeUpdate = deviceList.get(0);

        Type newType = typeService.getTypeByName("微型计算机");

        theDeviceTobeUpdate.setType(newType);

        deviceService.updateDeviceById(theDeviceTobeUpdate.getId(),theDeviceTobeUpdate);

    }
}