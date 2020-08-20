package cn.heyuantao.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author he_yu
 * 系统的启动类
 */

@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.heyuantao.main.mapper")
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
