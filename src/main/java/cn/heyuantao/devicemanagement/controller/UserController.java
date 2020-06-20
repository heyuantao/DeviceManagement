package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.User;
import cn.heyuantao.devicemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView list(Model model)
    {
        model.addAttribute("userList", userRepository.listUsers());
        model.addAttribute("title","用户管理");
        return new ModelAndView("users/list","userModel",model);
    }

    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model){
        User user = userRepository.getUserById(id);
        model.addAttribute("user",user);
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
}
