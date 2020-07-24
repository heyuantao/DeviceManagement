package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.util.Date;


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
    DeviceRepository deviceRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private void insertType(){
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

    private void insertOwner(){
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

    private void insertLocatioin(){
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

    private void insertUser(){
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

    private void insertDevice(){
        Device device = null;

        device = new Device();
        device.setName("Inspire2");
        device.setAssetNo("0xsdfs324fsdf");
        device.setSn("0121232432212");
        device.setVendor("大疆");
        device.setInDate(new Date(System.currentTimeMillis()));
        device.setUpdated(new Date(System.currentTimeMillis()));
        device.setLocation(locationRepository.findLocationByName("08A502"));
        device.setOwner(ownerRepository.findOwnerByName("李四"));
        device.setType(typeRepository.findTypeByName("无人机"));
        deviceRepository.save(device);

        try{
            device = new Device();
            device.setName("HP台式计算机");
            device.setAssetNo("0xsdfsdfsdfsdf");
            device.setSn("01212121212");
            device.setVendor("惠普");
            device.setInDate(new Date(System.currentTimeMillis()));
            device.setUpdated(new Date(System.currentTimeMillis()));
            device.setLocation(locationRepository.findLocationByName("08A506"));
            device.setOwner(ownerRepository.findOwnerByName("张三"));
            device.setType(typeRepository.findTypeByName("微型计算机"));
            deviceRepository.save(device);
        }catch (ConstraintViolationException ex){
            System.out.println("Exception Happen");
            System.out.println(ex.getStackTrace());
            System.out.println(ex.getMessage());
        }

    }

    private Boolean isDataExist(){
        Long count = deviceRepository.count();
        if(count>0){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    @Test
    public void initData(){
        if(!isDataExist()){
            insertOwner();

            insertLocatioin();
            insertUser();
            insertType();

            insertDevice();

            System.out.println("Insert new data !");
        }else{
            System.out.println("Data has exist !");
        }

    }


    @Test
    public void clearData(){
        deviceRepository.deleteAll();

        userRepository.deleteAll();
        typeRepository.deleteAll();
        ownerRepository.deleteAll();
        locationRepository.deleteAll();
    }
}