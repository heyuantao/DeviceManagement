package cn.heyuantao.devicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.heyuantao.devicemanagement.mapper")
@SpringBootApplication
@EnableSwagger2
public class DevicemanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevicemanagementApplication.class, args);
    }

}
