package com.project.pma.Controllers;

import com.project.pma.Entities.UserAccount;
import com.project.pma.Repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserAccountRepository userRepo;

    @GetMapping("/register")
    public String register(Model model){
        UserAccount userAccount=new UserAccount();
        model.addAttribute("userAccount",userAccount);
        return "Security/register";
    }

    @PostMapping("register/save")
    public String saveUser(Model model,UserAccount user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        return "redirect:/";
    }

}
