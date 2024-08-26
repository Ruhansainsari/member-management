package iit.java.membermgt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginValidationController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginValidation(@RequestParam String username, @RequestParam String password, Model model) {
        if ("admin".equals(username) && "admin123".equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("loginError", "Oops! Incorrect login details. Please and try again.");
            return "login";
        }
    }
}
