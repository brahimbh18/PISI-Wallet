package PISI_Wallet.PISI_Wallet.rowMappers;

import PISI_Wallet.PISI_Wallet.model.Post;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PostRowMapper implements RowMapper<Post> {

    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();

        post.setId(rs.getInt("id"));
        post.setAdminId(rs.getInt("adminId"));
        post.setTitle(rs.getString("title"));
        post.setBody(rs.getString("body"));
        post.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt")));

        return post;
    }
}
