package PISI_Wallet.PISI_Wallet.repository;

import PISI_Wallet.PISI_Wallet.model.Fund;
import PISI_Wallet.PISI_Wallet.rowMappers.FundRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FundRepository {
    private final JdbcTemplate jdbcTemplate;

    public FundRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createFund(Fund fund) {
        if (fund == null) {
            throw new IllegalArgumentException("User object cannot be null");
        }
        String sql = """
            INSERT INTO Transactions 
            (walletId, amount, date, cardId, adminId, type) 
            VALUES (?, ?, ?, ?, ?, 'FUND')
            """;

        try {
            // If adminId is 0, set it to NULL
            Integer finalAdminId = (fund.getAdminId() == 0) ? null : fund.getAdminId();

            jdbcTemplate.update(sql,
                    fund.getWalletId(),
                    fund.getAmount(),
                    fund.getDate(),
                    fund.getCardId(),
                    finalAdminId
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create fund: " + e.getMessage(), e);
        }

    }


    public List<Fund> getFundsByWalletId(int id) {
        String sql = "SELECT * FROM Transactions WHERE walletId = ? and type = 'FUND'";
        try {
            return jdbcTemplate.query(sql, new Object[]{id}, new FundRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch funds: " + e.getMessage(), e);
        }
    }
}