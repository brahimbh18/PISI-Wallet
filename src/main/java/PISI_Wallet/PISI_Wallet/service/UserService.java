package PISI_Wallet.PISI_Wallet.service;

import PISI_Wallet.PISI_Wallet.model.User;
import PISI_Wallet.PISI_Wallet.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private WalletService walletService;

    public UserService(UserRepository userRepository, WalletService walletService) {
        this.userRepository = userRepository;
        this.walletService = walletService;
    }

    public void createUser(User user, String walletName) {
        userRepository.createUser(user);
        user.setId(userRepository.getUserByCin(user.getCin()).getId());
        walletService.create(user, walletName);
    }

    public User getUserByCin(int cin) {
        return userRepository.getUserByCin(cin);
    }

    public User getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }

    public void updateUserByCin(User user) {
        userRepository.updateUserByCin(user);
    }

    public void deleteCusByEmail(int cin) {
        userRepository.deleteUserByCin(cin);
    }

    public User authenticate(String email, String password) {
        User user = userRepository.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return  null;
    }
}
