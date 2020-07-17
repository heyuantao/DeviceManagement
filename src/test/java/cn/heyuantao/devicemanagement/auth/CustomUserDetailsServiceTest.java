package cn.heyuantao.devicemanagement.auth;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.mapper.UserMapper;
import cn.heyuantao.devicemanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CustomUserDetailsServiceTest {

    @Resource
    UserMapper userMapper;

    @Resource
    UserService userService;

    public static String encode(String rawPassowrd){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(11);
        return bCryptPasswordEncoder.encode(rawPassowrd);
    }

    @Test
    void generateBCryptoPassword(){
        String rawPassword="123456";
        String hashPassword=encode(rawPassword);
        System.out.println(hashPassword);

        User user = userService.getUserByName("admin");
        user.setPassword(hashPassword);
        userMapper.updateByPrimaryKey(user);
        System.out.println("Update password finiashed !");

    }
}