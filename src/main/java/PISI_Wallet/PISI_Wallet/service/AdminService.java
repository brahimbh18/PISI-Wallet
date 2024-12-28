package PISI_Wallet.PISI_Wallet.service;

import PISI_Wallet.PISI_Wallet.repository.AdminRepository;
import PISI_Wallet.PISI_Wallet.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    private UserRepository userRepository;
    private AdminRepository adminRepository;

    public AdminService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }


}
