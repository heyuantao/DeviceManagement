package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.entity.Device;
import java.util.List;

public interface DeviceService {
    public List<Device> getDevices();
    public Device addDevice(Device device);
}
