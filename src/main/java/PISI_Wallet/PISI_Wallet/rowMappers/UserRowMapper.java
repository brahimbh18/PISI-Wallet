package PISI_Wallet.PISI_Wallet.rowMappers;


import PISI_Wallet.PISI_Wallet.model.User;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        // Mapping each column from the ResultSet to the User object
        user.setId(rs.getInt("id"));
        user.setCin(rs.getInt("cin"));
        user.setFirstName(rs.getString("firstName"));
        user.setLastName(rs.getString("lastName"));
        user.setDateOfBirth(rs.getString("dateOfBirth"));
        user.setPlaceOfBirth(rs.getString("placeOfBirth"));
        user.setGender(rs.getString("gender"));
        user.setPhoneNumber(rs.getString("phoneNumber"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));

        return user;
    }
}