package cn.heyuantao.main.service;

import cn.heyuantao.main.domain.Device;
import cn.heyuantao.main.domain.Location;
import cn.heyuantao.main.domain.Owner;
import cn.heyuantao.main.domain.Type;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

/*    @Test
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

            device.setLocationId(location.getId());
            device.setTypeId(type.getId());
            device.setOwnerId(owner.getId());

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

        System.out.println(theDeviceTobeUpdate);


        Type newType = typeService.getTypeByName("微型计算机");
        theDeviceTobeUpdate.setTypeId(newType.getId());
        deviceService.updateDeviceById(theDeviceTobeUpdate.getId(),theDeviceTobeUpdate);

    }*/
}