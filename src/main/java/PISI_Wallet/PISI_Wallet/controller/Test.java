package PISI_Wallet.PISI_Wallet.controller;


import PISI_Wallet.PISI_Wallet.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Test {

    @GetMapping("/user/welcome")
    public String test (Model model, HttpSession session) {
        System.out.println("welcome");
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        model.addAttribute("user", user);

        return "user/welcome";
    }

    @PostMapping("/logout")
    public String logout (Model model, HttpSession session) {
        session.invalidate();
        return "login.html";
    }
}
