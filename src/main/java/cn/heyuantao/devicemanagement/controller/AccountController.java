package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Account;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@RestController
public class AccountController {
    @RequestMapping("/accounts")
    public ModelAndView account(Model model){
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("abc","1212","admin","121212"));
        accounts.add(new Account("abc1","1212","admin","121212"));
        model.addAttribute("accounts",accounts);
        return new ModelAndView("account");
    }
}
