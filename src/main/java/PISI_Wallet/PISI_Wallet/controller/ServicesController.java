package PISI_Wallet.PISI_Wallet.controller;

import PISI_Wallet.PISI_Wallet.model.*;
import PISI_Wallet.PISI_Wallet.service.FundService;
import PISI_Wallet.PISI_Wallet.service.PaymentService;
import PISI_Wallet.PISI_Wallet.service.TransferService;
import PISI_Wallet.PISI_Wallet.service.WalletService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/services")
public class ServicesController {
    private final WalletService walletService;
    private final PaymentService paymentService;
    private final FundService fundService;
    private final TransferService transferService;

    public ServicesController(WalletService walletService, PaymentService paymentService, FundService fundService, TransferService transferService) {
        this.walletService = walletService;
        this.paymentService = paymentService;
        this.fundService = fundService;
        this.transferService = transferService;
    }

    @GetMapping("/payment")
    public String paymentForm() {
        return "user/services/payment";
    }

    @PostMapping("/payment")
    public String payment(@RequestParam String service, @RequestParam String customService, @RequestParam Double amount, HttpSession session, Model model) {
        Payment payment = new Payment();
        Wallet wallet = (Wallet) session.getAttribute("wallet");
        payment.setWalletId(wallet.getId());
        System.out.println("wallet id : " + wallet.getId());
        payment.setService((service.equals("other")) ? customService : service);
        payment.setAmount(amount);

        try {
            if (paymentService.createPayment(payment)) {
                return "redirect:/user/home?success";
            }
            model.addAttribute("errorMessage", "Insufficient funds for this payment.");
            return "user/services/payment";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            return "user/services/payment";
        }
    }

    @GetMapping("/fund")
    public String fundForm() {
        return "user/services/fund";
    }

    @PostMapping("/fund")
    public String fund(@ModelAttribute BankCard bankCard, @RequestParam Double amount, HttpSession session, Model model) {
        Fund fund = new Fund();
        Wallet wallet = (Wallet) session.getAttribute("wallet");
        fund.setWalletId(wallet.getId());
        fund.setAmount(amount);
        fund.setCardId(bankCard.getId());

        try {
            if (fundService.createFund(fund, bankCard)) {
                return "redirect:/user/home?success";
            }
            model.addAttribute("errorMessage", "Insufficient funds for this operation.");
            return "user/services/fund";
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            return "user/services/fund";
        }

    }

    @GetMapping("/transfer")
    public String transferForm() {
        return "user/services/transfer";
    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam int walletId, @RequestParam Double amount, HttpSession session, Model model) {
        Transfer transfer = new Transfer();
        Wallet wallet = (Wallet) session.getAttribute("wallet");

        transfer.setWalletId(wallet.getId());
        transfer.setReceivingWalletId(walletId);
        transfer.setAmount(amount);
        try {
            if (transferService.createTransfer(transfer)) {
                System.out.println("success");
                return "redirect:/user/home?success";
            }
            System.out.println("failure");
            model.addAttribute("errorMessage", "Transfer failed. Please check the details and try again.");
            return "user/services/transfer"; // Return to the transfer page with an error
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
            return "user/services/transfer"; // Return to the transfer page with a generic error
        }
    }
}
