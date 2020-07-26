package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Device;
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
class DeviceServiceImplTest {
    @Resource
    DeviceService deviceService;

    @Test
    void getDevices() {
        Map<String,Object> map = new HashMap<String,Object>();
        List<Device> deviceList = deviceService.selectDevicesByParams(map);
        for(Device oneDevice:deviceList){
            System.out.println(oneDevice);
        }
    }
}