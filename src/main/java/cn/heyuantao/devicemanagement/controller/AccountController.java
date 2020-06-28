package cn.heyuantao.devicemanagement.controller;

import cn.heyuantao.devicemanagement.domain.Account;
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
        List<Account> accounts = new ArrayList<>();
        accounts.add(new Account("abc","1212","admin","121212",new Date()));
        accounts.add(new Account("hyk","1212","admin","121212",new Date(System.currentTimeMillis())));
        accounts.add(new Account("hyt","1212","user","45454",new Date(System.currentTimeMillis())));
        model.addAttribute("accounts",accounts);
        model.addAttribute("info","This is the account information !");
        return new ModelAndView("account");
    }

    @RequestMapping("/account/1")
    @ResponseBody
    public Account oneUser(){
        Account oneAccount = new Account("abc","1212","admin","121212",new Date());
        return oneAccount;
    }

}
