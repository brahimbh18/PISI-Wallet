package PISI_Wallet.PISI_Wallet.controller;


import PISI_Wallet.PISI_Wallet.model.*;
import PISI_Wallet.PISI_Wallet.service.*;
import PISI_Wallet.PISI_Wallet.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final WalletService walletService;
    private final PaymentService paymentService;
    private final FundService fundService;
    private final TransferService transferService;
    private final PostService postService;

    public UserController(WalletService walletService, PaymentService paymentService, FundService fundService, TransferService transferService, PostService postService) {
        this.walletService = walletService;
        this.paymentService = paymentService;
        this.fundService = fundService;
        this.transferService = transferService;
        this.postService = postService;
    }

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Wallet> wallets = walletService.getWalletsByUserId(user.getId());
        if (user == null || wallets == null) {
            return "redirect:/login.html?error";
        }

        model.addAttribute("user", user);
        model.addAttribute("wallets", wallets);

        return "user/home";
    }

    @GetMapping("home/{id}")
    public String walletPage(@PathVariable int id, Model model, HttpSession session) {
        Wallet wallet = walletService.getWalletById(id);
        List<Payment> payments = paymentService.getPaymentsByWalletId(wallet.getId());
        List<Fund> funds = fundService.getFundsByWalletId(wallet.getId());
        List<Transfer> transfers = transferService.getTransfersByWalletId(wallet.getId());

        System.out.println(transfers);

        if (wallet == null || payments == null || funds == null || transfers == null) {
            return "redirect:?error";
        }
        session.setAttribute("wallet", wallet);
        session.setAttribute("payments", payments);
        session.setAttribute("funds", funds);
        session.setAttribute("transfers", transfers);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        String formattedDate = wallet.getCreatedAt().format(formatter);

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("wallet", wallet);
        model.addAttribute("createdAt", formattedDate);
        model.addAttribute("payments", payments);
        model.addAttribute("funds", funds);
        model.addAttribute("transfers", transfers);


        return "user/wallet";
    }

    @GetMapping("/news")
    public String news(HttpSession session, Model model) {

        model.addAttribute("user", session.getAttribute("user"));
        return "user/news";
    }

    @GetMapping("/addWallet")
    public String addWalletForm(HttpSession session, Model model) {

        return "user/addWallet";
    }

    @PostMapping("/addWallet")
    public String addWallet(HttpSession session, Model model, @RequestParam String walletName) {
        walletService.create((User) session.getAttribute("user"), walletName);
        return "redirect:home";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
