package cn.heyuantao.devicemanagement.controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class AccountController {
    @RequestMapping("/accounts")
    public ModelAndView account(Model model){
        model.addAttribute("info","This is the account information !");
        return new ModelAndView("account");
    }


}
