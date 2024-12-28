package PISI_Wallet.PISI_Wallet.repository;

import PISI_Wallet.PISI_Wallet.model.BankCard;
import PISI_Wallet.PISI_Wallet.rowMappers.BankCardRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BankCardRepository {
    JdbcTemplate jdbcTemplate;

    public BankCardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public void createCard(BankCard bankCard) {
        if (bankCard == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }
        String sql = """
                INSERT INTO BankCards 
                (cardNumber, cardHolder, expirationDate, cvv) 
                VALUES (?, ?, ?, ?)
                """;

        try {

            jdbcTemplate.update(sql,
                    bankCard.getCardNumber(),
                    bankCard.getCardHolder(),
                    bankCard.getExpirationDate(),
                    bankCard.getCvv()
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create bankCard: " + e.getMessage(), e);

        }



    }
    public BankCard getCardByNum(String num) {
        String sql = "SELECT * FROM BankCards WHERE cardNumber = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{num}, new BankCardRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}