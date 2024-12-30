package PISI_Wallet.PISI_Wallet.repository;

import PISI_Wallet.PISI_Wallet.model.Post;
import PISI_Wallet.PISI_Wallet.rowMappers.PostRowMapper;
import PISI_Wallet.PISI_Wallet.rowMappers.UserRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepository {
    private final JdbcTemplate jdbcTemplate;

    public PostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> getAllPosts() {
        String sql = "SELECT * FROM News;";
        try {
            return jdbcTemplate.query(sql, new PostRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch posts: " + e.getMessage(), e);
        }
    }
}
