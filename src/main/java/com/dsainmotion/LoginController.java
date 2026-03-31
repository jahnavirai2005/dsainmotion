package com.dsainmotion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import com.dsainmotion.entity.User;
import com.dsainmotion.repository.UserRepository;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    // Helper method to generate user initials
    private String getInitials(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            return "U";
        }
        return String.valueOf(Character.toUpperCase(firstName.trim().charAt(0)));
    }

    // Helper method to load user data and add to model
    private void loadUserData(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            User user = userRepository.getUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
                model.addAttribute("name", user.getFirstName());
                model.addAttribute("email", user.getEmail());
                model.addAttribute("initials", getInitials(user.getFirstName()));
            }
        }
    }

    // Helper method to check if user is authenticated
    private String checkAuth(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        return null;
    }

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/dashboard";
        }
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String landing(HttpSession session, Model model) {
        if (session.getAttribute("userId") != null) {
            return "redirect:/dashboard";
        }
        model.addAttribute("activePage", "home");
        loadUserData(session, model);
        return "landing";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "next", required = false) String next, Model model) {
        if (next != null && !next.trim().isEmpty()) {
            model.addAttribute("next", next.trim());
        }
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {
        String redirect = checkAuth(session);
        if (redirect != null) return redirect;
        model.addAttribute("activePage", "home");
        loadUserData(session, model);
        return "dashboard";
    }


    @GetMapping("/help")
    public String help(HttpSession session, Model model) {
        model.addAttribute("activePage", "help");
        loadUserData(session, model);
        return "help";
    }

    @GetMapping("/about")
    public String about(HttpSession session, Model model) {
        model.addAttribute("activePage", "about");
        loadUserData(session, model);
        return "about";
    }


    @GetMapping("/stack")
    public String stack(HttpSession session, Model model) {
        String redirect = checkAuth(session);
        if (redirect != null) return redirect;
        model.addAttribute("activePage", "home");
        loadUserData(session, model);
        return "stack";
    }

    @GetMapping("/queue")
    public String queue(HttpSession session, Model model) {
        String redirect = checkAuth(session);
        if (redirect != null) return redirect;
        model.addAttribute("activePage", "home");
        loadUserData(session, model);
        return "queue";
    }

    @GetMapping("/tree")
    public String tree(HttpSession session, Model model) {
        String redirect = checkAuth(session);
        if (redirect != null) return redirect;
        model.addAttribute("activePage", "home");
        loadUserData(session, model);
        return "tree";
    }

    @GetMapping("/sorting")
    public String sorting(HttpSession session, Model model) {
        String redirect = checkAuth(session);
        if (redirect != null) return redirect;
        model.addAttribute("activePage", "home");
        loadUserData(session, model);
        return "sorting";
    }

    @GetMapping("/searching")
    public String searching(HttpSession session, Model model) {
        String redirect = checkAuth(session);
        if (redirect != null) return redirect;
        model.addAttribute("activePage", "home");
        loadUserData(session, model);
        return "searching";
    }

    @GetMapping("/array")
    public String array(HttpSession session, Model model) {
        String redirect = checkAuth(session);
        if (redirect != null) return redirect;
        model.addAttribute("activePage", "home");
        loadUserData(session, model);
        return "array";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/home";
    }

    @PostMapping("/login")
    public String loginUser(
            @RequestParam(name = "userid") String userid,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "next", required = false) String next,
            Model model,
            HttpSession session) {

        User user = userRepository.getUserById(userid);

        if (user != null && user.getPass().equals(password)) {
            session.setAttribute("userId", user.getUserId());
            model.addAttribute("name", user.getFirstName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("initials", getInitials(user.getFirstName()));
            if (next != null && !next.trim().isEmpty()) {
                return "redirect:/" + next;
            }
            return "redirect:/dashboard";
        } else {
            String errorUrl = "/login?error=true";
            if (next != null && !next.trim().isEmpty()) {
                errorUrl += "&next=" + next;
            }
            return "redirect:" + errorUrl;
        }
    }

    @GetMapping("/register")
    public String registerPage(
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "success", required = false) String success,
            Model model) {

        if ("userexists".equals(error)) {
            model.addAttribute("message", "User ID already exists. Please choose another.");
            model.addAttribute("messageType", "error");
        } else if ("emailexists".equals(error)) {
            model.addAttribute("message", "Email already registered. Please login or use a different email.");
            model.addAttribute("messageType", "error");
        } else if ("true".equals(error)) {
            model.addAttribute("message", "Registration failed due to server error. Please try again.");
            model.addAttribute("messageType", "error");
        } else if (success != null) {
            model.addAttribute("message", "Registration successful! Please login.");
            model.addAttribute("messageType", "success");
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @RequestParam(name = "user_id") String user_id,
            @RequestParam(name = "first_name") String first_name,
            @RequestParam(name = "last_name") String last_name,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "phone") String phone,
            @RequestParam(name = "pass") String pass,
            Model model) {

        System.out.println("[registerUser] userid='" + user_id + "' email='" + email + "'");

        // Preserve form data
        model.addAttribute("user_id", user_id);
        model.addAttribute("first_name", first_name);
        model.addAttribute("last_name", last_name);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);

        if (userRepository.existsByUserId(user_id)) {
            model.addAttribute("message", "User ID already exists. Please choose another.");
            model.addAttribute("messageType", "error");
            return "register";
        }
        if (userRepository.existsByEmail(email)) {
            model.addAttribute("message", "Email already registered. Please login or use a different email.");
            model.addAttribute("messageType", "error");
            return "register";
        }

        try {
            User user = new User();
            user.setUserId(user_id);
            user.setFirstName(first_name);
            user.setLastName(last_name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPass(pass);

            userRepository.save(user);
            System.out.println("[registerUser] success user saved: " + user_id);
            return "redirect:/login?success=true";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Registration failed due to server error. Please try again.");
            model.addAttribute("messageType", "error");
            return "register";
        }
    }

}