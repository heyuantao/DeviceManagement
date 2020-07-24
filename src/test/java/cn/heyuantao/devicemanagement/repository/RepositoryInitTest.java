package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.Location;
import cn.heyuantao.devicemanagement.entity.Owner;
import cn.heyuantao.devicemanagement.entity.Type;
import cn.heyuantao.devicemanagement.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
class RepositoryInitTest {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void insertType(){
        Type type=null;

        type=new Type();
        type.setName("无人机");
        type.setDescription("电动微型无人机");
        typeRepository.save(type);

        type=new Type();
        type.setName("微型计算机");
        type.setDescription("台式计算机");
        typeRepository.save(type);
    }

    public void insertOwner(){
        Owner owner = null;

        owner = new Owner();
        owner.setName("张三");
        owner.setDepartment("软件工程系");
        owner.setDescription("测试用户");
        ownerRepository.save(owner);

        owner = new Owner();
        owner.setName("李四");
        owner.setDepartment("网络工程系");
        owner.setDescription("测试用户");
        ownerRepository.save(owner);
    }

    public void insertLocatioin(){
        Location location=null;

        location=new Location();
        location.setName("08A502");
        location.setDescription("计算机组成原理实验");
        locationRepository.save(location);

        location=new Location();
        location.setName("08A506");
        location.setDescription("网络安全实验室");
        locationRepository.save(location);
    }

    public void insertUser(){
        User user= null;

        user = new User();
        user.setName("admin");
        user.setEmail("admin@example.com");
        user.setSuperuser(Boolean.TRUE);
        user.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(user);

        user = new User();
        user.setName("abc");
        user.setEmail("abc@example.com");
        user.setSuperuser(Boolean.FALSE);
        user.setPassword(passwordEncoder.encode("123456"));
        userRepository.save(user);
    }

    public void insertDevice(){
    }

    @Test
    public void initData(){
        //insertOwner();
        //insertLocatioin();
        //insertUser();
    }
}