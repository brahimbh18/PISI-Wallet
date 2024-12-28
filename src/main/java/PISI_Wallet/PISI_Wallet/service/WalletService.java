package PISI_Wallet.PISI_Wallet.service;

import PISI_Wallet.PISI_Wallet.model.User;
import PISI_Wallet.PISI_Wallet.model.Wallet;
import PISI_Wallet.PISI_Wallet.repository.UserRepository;
import PISI_Wallet.PISI_Wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class WalletService {
    private WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public void create(User user, String walletName) {
        Wallet wallet = walletInit(user, walletName);
        walletRepository.createWallet(wallet);
    }

    public Wallet getWalletById(int id) {
        return walletRepository.getWalletById(id);
    }

    public List<Wallet> getWalletsByUserId(int id) {
        return walletRepository.getWalletsByUserId(id);
    }

    private Wallet walletInit(User user, String walletName) {
        Wallet wallet = new Wallet();
        wallet.setUserId(user.getId());
        wallet.setName(walletName);
        wallet.setBalance(0);
        wallet.setCode(getNewCode());
        return wallet;
    }

    private String getNewCode() {
        Random rand = new Random();
        int new_code = rand.nextInt(1000);

        while (walletRepository.getWalletByCode(new_code) != null) {
            new_code = rand.nextInt(1000);
        }

        return  String.valueOf(new_code);

    }


    public void updateBalanceById(int id, double newBalance) {
        walletRepository.updateWalletBalanceById(id, newBalance);
    }

    public double getBalanceByid(int walletId) {
        return walletRepository.getBalanceById(walletId);
    }
}
