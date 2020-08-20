package cn.heyuantao.main.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestForLog {

    @Test
    public void testForLog(){
        log.error("Error Happen !");
        log.warn("Warning ");
        log.info("Information ");
    }
}
