package cn.heyuantao.devicemanagement.util;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class JsonWebTokenUtilTest {

    @Resource
    JsonWebTokenUtil jsonWebTokenUtil;

    @Test
    void generateToken() {
        String username = "abc";
        String token;
        token = jsonWebTokenUtil.generateToken(username);
        System.out.println(token);
    }
}