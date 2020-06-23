package cn.heyuantao.devicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.heyuantao.devicemanagement.mapper")
@SpringBootApplication
public class DevicemanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevicemanagementApplication.class, args);
    }

}
