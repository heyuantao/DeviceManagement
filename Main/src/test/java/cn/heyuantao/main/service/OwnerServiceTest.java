package cn.heyuantao.main.service;

import cn.heyuantao.main.domain.Owner;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class OwnerServiceTest {

    @Resource
    OwnerService ownerService;

    @Test
    void getOwners() {
        List<Owner> ownerList=ownerService.getOwners();
        for(Owner oneOwner:ownerList){
            System.out.println(oneOwner);
        }
    }


    @Test
    @Transactional
    void testGetOwners() {
        Map<String,Object> params = new HashMap<String,Object>();
        Map<String,Object> selectMap = new HashMap<String,Object>();
        Map<String,Object> searchMap = new HashMap<String,Object>();
        //selectMap.put("name","hyt");
        selectMap.put("department","网络工程系");
        searchMap.put("search","四");
        params.put("selectMap",selectMap);
        params.put("searchMap",searchMap);
        List<Owner> ownerList = ownerService.getOwnersByParams(params);
        for(Owner owner:ownerList){
            System.out.println(owner);
        }
    }
}