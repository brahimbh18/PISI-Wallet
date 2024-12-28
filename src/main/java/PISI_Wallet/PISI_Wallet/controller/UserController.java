package PISI_Wallet.PISI_Wallet.controller;


import PISI_Wallet.PISI_Wallet.model.*;
import PISI_Wallet.PISI_Wallet.service.*;
import PISI_Wallet.PISI_Wallet.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final WalletService walletService;
    private final PaymentService paymentService;
    private final FundService fundService;
    private final TransferService transferService;

    public UserController(WalletService walletService, PaymentService paymentService, FundService fundService, TransferService transferService) {
        this.walletService = walletService;
        this.paymentService = paymentService;
        this.fundService = fundService;
        this.transferService = transferService;
    }

    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<Wallet> wallets = walletService.getWalletsByUserId(user.getId());
        if (user == null || wallets == null) {
            return "redirect:/login?error";
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

        System.out.println(funds);

        if (wallet == null || payments == null || funds == null) {
            return "redirect:?error";
        }
        session.setAttribute("wallet", wallet);
        session.setAttribute("payments", payments);
        session.setAttribute("funds", funds);

        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("wallet", wallet);
        model.addAttribute("payments", payments);
        model.addAttribute("funds", funds);

        return "user/wallet";
    }

    @GetMapping("/payment")
    public String paymentForm() {
        return "user/payment";
    }

    @PostMapping("/payment")
    public String payment(@RequestParam String service, @RequestParam String customService, @RequestParam Double amount, HttpSession session) {
        Payment payment = new Payment();
        Wallet wallet = (Wallet) session.getAttribute("wallet");
        payment.setWalletId(wallet.getId());
        System.out.println("wallet id : " + wallet.getId());
        payment.setService((service.equals("other")) ? customService : service);
        payment.setAmount(amount);
        if (paymentService.createPayment(payment)) {
            return "redirect:home?success";
        }
        return "redirect:payement?error";
    }

    @GetMapping("/fund")
    public String fundForm() {
        return "user/fund";
    }

    @PostMapping("/fund")
    public String fund(@ModelAttribute BankCard bankCard, @RequestParam Double amount, HttpSession session) {
        Fund fund = new Fund();
        Wallet wallet = (Wallet) session.getAttribute("wallet");
        fund.setWalletId(wallet.getId());
        fund.setAmount(amount);
        fund.setCardId(bankCard.getId());

        if (fundService.createFund(fund, bankCard)) {
            return "redirect:home?success";
        }
        return "redirect:fund?error";
    }

    @GetMapping("/transfer")
    public String transferForm() {
        return "user/transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam int walletId, @RequestParam Double amount, HttpSession session) {
        Transfer transfer = new Transfer();
        Wallet wallet = (Wallet) session.getAttribute("wallet");

        transfer.setWalletId(wallet.getId());
        transfer.setReceivingWalletId(walletId);
        transfer.setAmount(amount);
        if (transferService.createTransfer(transfer)) {
            return "redirect:home?success";
        }
        return "redirect:transfer?error";
    }
}
