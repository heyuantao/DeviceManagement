/*
package cn.heyuantao.devicemanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pagehelper.PageHelper;

import java.util.Properties;

*/
/**分页的配置类
 * @author he_yu
 *
 *//*

@Configuration
public class PageHelperConfig {

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("helpDialect","mysql");
        properties.setProperty("reasonable","true");
        properties.setProperty("supportMethodsArguments","true");
        properties.setProperty("rowBoundsWithCount","true");
        properties.setProperty("params","count=countSql");
        properties.setProperty("pageSizeZero","true");
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
*/
