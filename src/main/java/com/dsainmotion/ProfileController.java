package com.dsainmotion;

import com.dsainmotion.entity.User;
import com.dsainmotion.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ProfileController {

    @Autowired
    private UserRepository userRepository;

    private void loadUserData(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if (userId != null) {
            User user = userRepository.getUserById(userId);
            if (user != null) {
                String firstName = user.getFirstName() != null ? user.getFirstName().trim() : "User";
                model.addAttribute("name", firstName);
                model.addAttribute("email", user.getEmail());
                String initials = firstName.isEmpty() ? "U" : String.valueOf(Character.toUpperCase(firstName.charAt(0)));
                model.addAttribute("initials", initials);
                model.addAttribute("user", user); // Pass full user for form binding
            }
        }
    }

    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }
        model.addAttribute("activePage", "profile");
        loadUserData(session, model);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(
            @RequestParam(name = "firstName") String firstName,
            @RequestParam(name = "lastName") String lastName,
            @RequestParam(name = "email") String email,
            @RequestParam(name = "phone") String phone,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        String userId = (String) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        User user = userRepository.getUserById(userId);
        if (user != null) {
            // Validation
            if (!firstName.matches("^[A-Za-z]+$") || !lastName.matches("^[A-Za-z]+$")) {
                redirectAttributes.addFlashAttribute("error", "Name must contain only alphabets.");
                return "redirect:/profile";
            }
            if (!phone.matches("^\\d{10}$")) {
                redirectAttributes.addFlashAttribute("error", "Phone must be exactly 10 digits.");
                return "redirect:/profile";
            }
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                redirectAttributes.addFlashAttribute("error", "Enter a valid email address.");
                return "redirect:/profile";
            }

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhone(phone);
            userRepository.save(user);
            redirectAttributes.addFlashAttribute("success", "Profile updated successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "User not found.");
        }
        
        return "redirect:/profile";
    }

    @PostMapping("/profile/change-password")
    public String changePassword(
            @RequestParam(name = "currentPassword") String currentPassword,
            @RequestParam(name = "newPassword") String newPassword,
            @RequestParam(name = "confirmPassword") String confirmPassword,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
        
        String userId = (String) session.getAttribute("userId");
        if (userId == null) return "redirect:/login";

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("pwdError", "New passwords do not match.");
            return "redirect:/profile";
        }

        // Strong Password Validation
        if (!newPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&]).{8,}$")) {
            redirectAttributes.addFlashAttribute("pwdError", "Password must be 8+ chars with A-Z, a-z, 0-9 & special char.");
            return "redirect:/profile";
        }

        User user = userRepository.getUserById(userId);
        if (user != null) {
            if (user.getPass().equals(currentPassword)) {
                user.setPass(newPassword);
                userRepository.save(user);
                redirectAttributes.addFlashAttribute("pwdSuccess", "Password changed successfully!");
            } else {
                redirectAttributes.addFlashAttribute("pwdError", "Current password is incorrect.");
            }
        } else {
            redirectAttributes.addFlashAttribute("pwdError", "User not found.");
        }

        return "redirect:/profile";
    }
}
