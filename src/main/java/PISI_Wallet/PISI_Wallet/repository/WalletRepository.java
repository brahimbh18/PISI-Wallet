package PISI_Wallet.PISI_Wallet.repository;

import PISI_Wallet.PISI_Wallet.model.Wallet;
import PISI_Wallet.PISI_Wallet.rowMappers.WalletRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WalletRepository {
    JdbcTemplate jdbcTemplate;

    public WalletRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createWallet(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }

        String sql = "INSERT INTO Wallets (userId, name, balance, createdAt, code) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            jdbcTemplate.update(sql,
                    wallet.getUserId(),
                    wallet.getName(),
                    wallet.getBalance(),
                    wallet.getCreatedAt(),
                    wallet.getCode()
            );

        } catch (Exception e) {
            System.err.println("Failed to create wallet: " + e.getMessage());
            throw new RuntimeException("Error creating wallet", e);

        }
    }

    public Wallet getWalletById(int id) {
        String sql = "SELECT * FROM Wallets WHERE id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new WalletRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Wallet> getWalletsByUserId(int id) {
        String sql = "SELECT * FROM Wallets WHERE userId = ?";
        try {
            return jdbcTemplate.query(sql, new Object[]{id}, new WalletRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Wallet getWalletByCode(int code) {
        String sql = "SELECT * FROM Wallets WHERE code = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{code}, new WalletRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateWalletBalanceById(int id, double newBalance) {
        String sql = """
            UPDATE Wallets
            SET balance = balance + ? 
            WHERE id = ?
            """;

        try {
            int rowsUpdated = jdbcTemplate.update(sql, newBalance, id);
            if (rowsUpdated == 0) {
                throw new IllegalArgumentException("No card found with the given id: " + id);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to update wallet balance: " + e.getMessage(), e);
        }
    }

    public double getBalanceById(int walletId) {
        String sql = "SELECT * FROM Wallets WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{walletId}, new WalletRowMapper()).getBalance();
        }
}
