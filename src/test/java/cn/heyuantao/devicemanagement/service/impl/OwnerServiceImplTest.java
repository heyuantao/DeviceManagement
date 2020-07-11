package cn.heyuantao.devicemanagement.service.impl;

import cn.heyuantao.devicemanagement.domain.Owner;
import cn.heyuantao.devicemanagement.service.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class OwnerServiceImplTest {

    @Resource
    OwnerService ownerService;

    @Test
    void getOwners() {
        List<Owner> ownerList=ownerService.getOwners();
        for(Owner oneOwner:ownerList){
            System.out.println(oneOwner);
        }
    }
}