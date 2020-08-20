package cn.heyuantao.main.service;

import cn.heyuantao.main.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Resource
    UserService userService;

    @Test
    void getUsers() {
        List<User> userList = userService.getUsers();
        System.out.println("Show users !");
        for(User oneUser:userList){
            System.out.println(oneUser);
        }
        System.out.println("Finished !");
    }

    @Test
    @Transactional
    void createUsers() {
        User oneUser= new User();
        oneUser.setName("zua");
        oneUser.setPassword("zua");
        oneUser.setEmail("zua@example.com");
        oneUser.setSuperuser(true);
        User createdUser = userService.addUser(oneUser);
        System.out.println(createdUser);
    }
}



//@MybatisTest
//@AutoConfigureTestDatabase
//@ContextConfiguration("classpath:/applicationContext.xml")