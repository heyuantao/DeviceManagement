package cn.heyuantao.main.util;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
class JsonWebTokenUtilTest {

    JsonWebTokenUtil jsonWebTokenUtil = new JsonWebTokenUtil();

    @Test
    void generateTokenWithName() {
        String username = "abc";
        String token;
        token = jsonWebTokenUtil.generateToken(username);
        System.out.println("Token is :"+token);
        Assert.assertEquals(jsonWebTokenUtil.validateToken(token,"abc"),Boolean.TRUE);
        Assert.assertEquals(jsonWebTokenUtil.extractUsername(token),"abc");
    }

    @Test
    void extractExpiration() {
        String username = "abc";
        String token;
        token = jsonWebTokenUtil.generateToken(username);
        Date date= jsonWebTokenUtil.extractExpiration(token);
        System.out.println("Token expire at:"+date);
    }

}