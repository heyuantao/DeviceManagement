package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("devices")
public class DeviceController {
    @Autowired
    private DeviceRepository deviceRepository;

    @GetMapping
    public List<Device> list(){
        deviceRepository.getClass();
        return new ArrayList<>();
    }
}
