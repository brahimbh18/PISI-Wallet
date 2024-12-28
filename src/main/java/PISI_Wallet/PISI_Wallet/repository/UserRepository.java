package PISI_Wallet.PISI_Wallet.repository;

import PISI_Wallet.PISI_Wallet.model.User;
import PISI_Wallet.PISI_Wallet.rowMappers.UserRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void createUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }

        String sql = "INSERT INTO Users (cin, firstName, lastName, dateOfBirth, placeOfBirth," +
                "gender, phoneNumber, email, password) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql,
                    user.getCin(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getDateOfBirth(),
                    user.getPlaceOfBirth(),
                    user.getGender(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getPassword()
            );

        } catch (Exception e) {
            System.err.println("Failed to create user: " + e.getMessage());
            throw new RuntimeException("Error creating user", e);

        }
    }

    public User getUserByCin(int cin) {
        String sql = "SELECT * FROM Users WHERE cin = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{cin}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{email}, new UserRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null; // No user found with that email
        }
    }

    public void updateUserByCin(User user) {
    }

    public void deleteUserByCin(int cin) {
        
    }
}
