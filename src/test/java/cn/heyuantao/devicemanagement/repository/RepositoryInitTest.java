package cn.heyuantao.devicemanagement.repository;

import cn.heyuantao.devicemanagement.entity.Type;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
class RepositoryInitTest {

    @Autowired
    TypeRepository typeRepository;

/*    @Test
    public void insertData(){

        Type type1=new Type();
        type1.setName("无人机");
        type1.setDescription("电动微型无人机");
        typeRepository.save(type1);

        Type type2=new Type();
        type2.setName("微型计算机");
        type2.setDescription("台式计算机");
        typeRepository.save(type2);
    }*/
}