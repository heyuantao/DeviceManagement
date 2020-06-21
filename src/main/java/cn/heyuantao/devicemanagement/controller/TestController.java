package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView list(Model model)
    {
        //model.addAttribute("userList", userRepository.listUsers());
        //model.addAttribute("title","用户管理");
        return new ModelAndView("test/index");
    }

}
