package cn.heyuantao.devicemanagement.service;

import cn.heyuantao.devicemanagement.Mapper.UserMapper;
import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.repository.UserRepository;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


@Service
public class UserServiceImpl {

    @Autowired
    private UserRepository userRepository;
    @Resource
    private UserMapper userMapper;

    public void checkLogin(String username,String password) throws Exception{
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        subject.login(token);
    }
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
    public Subject getSubject(){
        Subject subject = SecurityUtils.getSubject();
        return subject;
    }
    public String getPasswordByUserName(String username){
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("name",username);
        List<User> users = userMapper.selectByExample(example);
        return users.get(0).getPassword();
    }
}
