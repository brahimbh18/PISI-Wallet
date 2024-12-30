package PISI_Wallet.PISI_Wallet.service;

import PISI_Wallet.PISI_Wallet.model.Post;
import PISI_Wallet.PISI_Wallet.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }


    public List<Post> getAllPosts() {
        return  postRepository.getAllPosts();
    }
}
