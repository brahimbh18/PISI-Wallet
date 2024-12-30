package PISI_Wallet.PISI_Wallet.service;

import PISI_Wallet.PISI_Wallet.model.Admin;
import PISI_Wallet.PISI_Wallet.model.User;
import PISI_Wallet.PISI_Wallet.repository.AdminRepository;
import PISI_Wallet.PISI_Wallet.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private UserRepository userRepository;
    private AdminRepository adminRepository;

    public AdminService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }


    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }


}
