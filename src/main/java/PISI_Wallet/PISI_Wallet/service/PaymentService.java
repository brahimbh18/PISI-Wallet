package PISI_Wallet.PISI_Wallet.service;

import PISI_Wallet.PISI_Wallet.model.Payment;
import PISI_Wallet.PISI_Wallet.model.Wallet;
import PISI_Wallet.PISI_Wallet.repository.PaymentRepository;
import PISI_Wallet.PISI_Wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final WalletService walletService;

    public PaymentService(PaymentRepository paymentRepository, WalletService walletService) {
        this.paymentRepository = paymentRepository;
        this.walletService = walletService;
    }

    public boolean createPayment(Payment payment) {
        double balance = walletService.getBalanceByid(payment.getWalletId());
        if (balance < payment.getAmount()) {
            return false;
        }
        paymentRepository.createPayment(payment);
        walletService.updateBalanceById(payment.getWalletId(), payment.getAmount() * -1);
        return true;
    }


    public List<Payment> getPaymentsByWalletId(int id) {
        return paymentRepository.getPaymentsByWalletId(id);
    }
}
