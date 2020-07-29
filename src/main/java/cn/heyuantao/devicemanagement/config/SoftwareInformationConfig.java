package cn.heyuantao.devicemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author he_yu
 */
@Configuration
public class SoftwareInformationConfig {

    @Bean
    public SoftwareInformation getSoftware(){
        SoftwareInformation softwareInformation=new SoftwareInformation();
        softwareInformation.setVersion("0.1");
        softwareInformation.setTitle("实验室设备管理");
        softwareInformation.setDescription("管理设备的借用和存放,该项目是一个示例性项目，用于延时Spring Boot、Spring Security和Mybasits的使用。");
        //System.out.println(softwareInformation);
        return softwareInformation;
    }
}
