package PISI_Wallet.PISI_Wallet.controller;

import PISI_Wallet.PISI_Wallet.model.Admin;
import PISI_Wallet.PISI_Wallet.model.Post;
import PISI_Wallet.PISI_Wallet.model.User;
import PISI_Wallet.PISI_Wallet.service.AdminService;
import PISI_Wallet.PISI_Wallet.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService  adminService;
    private final PostService postService;

    public AdminController(AdminService adminService, PostService postService) {
        this.adminService = adminService;
        this.postService = postService;
    }

    @GetMapping("/users")
    public String showUsers(HttpSession session, Model model) {
        List<User> users = adminService.getAllUsers();

        for (User user: users) {
            user.toString();
        }
        model.addAttribute("users", users);
        return "admin/users";
    }

    @GetMapping("/news")
    public String showNews(HttpSession session, Model model) {
        List<Post> news = postService.getAllPosts();

        model.addAttribute("news", news);
        return "admin/news";
    }

    @GetMapping("/transactions")
    public String showTransactions(HttpSession session, Model model) {
        List<User> users = adminService.getAllUsers();

        for (User user: users) {
            user.toString();
        }
        model.addAttribute("users", users);
        return "admin/users";
    }
}
