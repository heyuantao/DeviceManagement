package cn.heyuantao.devicemanagement.config;

import cn.heyuantao.devicemanagement.domain.SoftwareInformation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @author he_yu
 */
@Configuration
public class SoftwareInformationConfig {
    @Value("${profile.name:dev}")
    private String runStatus;

    @Bean
    public SoftwareInformation getSoftware(){
        SoftwareInformation softwareInformation=new SoftwareInformation();
        softwareInformation.setVersion("0.1");
        softwareInformation.setTitle("实验室设备管理");
        softwareInformation.setDescription("管理设备的借用和存放,该项目是一个示例性项目，用于延时Spring Boot、Spring Security和Mybasits的使用");

        /**
         * 设置在软件基本信息界面显示的内容
         */
        if(StringUtils.equalsIgnoreCase(runStatus, "dev")){
            softwareInformation.setRunStatus("调试模式");
        }else if(StringUtils.equalsIgnoreCase(runStatus, "prod")){
            softwareInformation.setRunStatus("发布模式");
        }else{
            softwareInformation.setRunStatus("异常模式");
        }

        return softwareInformation;
    }
}
