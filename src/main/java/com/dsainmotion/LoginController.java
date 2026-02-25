package com.dsainmotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dsainmotion.entity.User;
import com.dsainmotion.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;   // ✅ lowercase variable

    @GetMapping("/home")
    public String landing() {
        return "landing";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam String user_id,
            @RequestParam String first_name,
            @RequestParam String last_name,
            @RequestParam String email,
            @RequestParam String pass
    ) {
        User user = new User();
        user.setUser_id(user_id);
        user.setFirst_name(first_name);
        user.setLast_name(last_name);
        user.setEmail(email);
        user.setPass(pass);

        userRepository.save(user);   // ✅ correct call

        return "login";
    }
}