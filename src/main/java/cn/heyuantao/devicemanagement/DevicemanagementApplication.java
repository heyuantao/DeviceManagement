package cn.heyuantao.devicemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author he_yu
 * 系统的启动类，使用了Mybatis的通用Mapper，缓存
 */

@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.heyuantao.devicemanagement.mapper")
@SpringBootApplication
public class DevicemanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevicemanagementApplication.class, args);
    }

}
