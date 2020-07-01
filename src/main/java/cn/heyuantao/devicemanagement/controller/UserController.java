package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView list(Model model)
    {
        model.addAttribute("userList", userRepository.findAll());
        model.addAttribute("title","用户管理");
        return new ModelAndView("users/list","userModel",model);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        Optional<User> useResult = userRepository.findById(id);
        if(!useResult.isPresent()){
            System.out.println("Not Exist");
        }
        model.addAttribute("user",useResult.get());
        model.addAttribute("title","查看用户");
        return new ModelAndView("users/view","userModel",model);
    }

    @GetMapping("/form")
    public ModelAndView createForm(Model model){
        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("title","创建用户");
        return new ModelAndView("users/form","userModel",model);
    }

    @PostMapping
    public ModelAndView create(User user){
        user = userRepository.save(user);
        return new ModelAndView("redirect:/users");
    }

    @GetMapping(value="delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, Model model){
        userRepository.deleteById(id);
        model.addAttribute("userList",userRepository.findAll());
        model.addAttribute("title","删除用户");
        return new ModelAndView("users/list","userModel",model);
    }

    @GetMapping(value="modify/{id}")
    public ModelAndView modifyForm(@PathVariable("id") Long id, Model model){
        Optional<User> userResult = userRepository.findById(id);
        if(!userResult.isPresent()){
            System.out.println("Error happen !");
        }
        model.addAttribute("user",userResult.get());
        model.addAttribute("title","修改用户");
        return new ModelAndView("users/form","userModel",model);
    }
}
