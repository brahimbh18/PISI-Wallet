package PISI_Wallet.PISI_Wallet.controller;

import PISI_Wallet.PISI_Wallet.model.User;
import PISI_Wallet.PISI_Wallet.model.Wallet;
import PISI_Wallet.PISI_Wallet.service.UserService;
import PISI_Wallet.PISI_Wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AuthController {
    private final UserService userService;
    private final WalletService walletService;

    public AuthController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
    }
    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, Model model, HttpSession session)  {
        User user = userService.authenticate(email, password);
        if (user != null) {
            List<Wallet> wallets = walletService.getWalletsByUserId(user.getId());
            session.setAttribute("user", user);
            session.setAttribute("wallets", wallets);

            return "redirect:user/home";
        }
        return "redirect:login?error=invalidCredentials";
    }


    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, @RequestParam String walletName, @RequestParam String password) {
        if (user.getPassword().equals(password)) {
            if (userService.getUserByCin(user.getCin()) != null) {
                return "redirect:register?error=CinAlreadyUsed";
            } else if (userService.getUserByEmail(user.getEmail()) != null) {
                return "redirect:register?error=EmailAlreadyUsed";
            } else {
                userService.createUser(user, walletName);
                return "redirect:login";
            }

        } else {
            return "redirect:register?error=passwordsDontMatch";
        }
    }
}
