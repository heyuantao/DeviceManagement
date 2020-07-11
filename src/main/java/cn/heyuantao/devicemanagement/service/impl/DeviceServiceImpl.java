package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Device;
import cn.heyuantao.devicemanagement.mapper.DeviceMapper;
import cn.heyuantao.devicemanagement.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Resource
    DeviceMapper deviceMapper;

    @Override
    public List<Device> getDevices() {
        return deviceMapper.selectAll();
    }
}
