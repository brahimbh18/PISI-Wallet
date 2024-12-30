package PISI_Wallet.PISI_Wallet.rowMappers;


import PISI_Wallet.PISI_Wallet.model.Admin;
import PISI_Wallet.PISI_Wallet.model.Admin;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {

    @Override
    public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
        Admin admin = new Admin();

        // Mapping each column from the ResultSet to the Admin object
        admin.setId(rs.getInt("id"));
        admin.setCin(rs.getInt("cin"));
        admin.setFirstName(rs.getString("firstName"));
        admin.setLastName(rs.getString("lastName"));
        admin.setDateOfBirth(rs.getString("dateOfBirth"));
        admin.setPlaceOfBirth(rs.getString("placeOfBirth"));
        admin.setGender(rs.getString("gender"));
        admin.setPhoneNumber(rs.getString("phoneNumber"));
        admin.setEmail(rs.getString("email"));
        admin.setPassword(rs.getString("password"));

        return admin;
    }
}