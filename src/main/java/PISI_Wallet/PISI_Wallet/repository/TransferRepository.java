package PISI_Wallet.PISI_Wallet.repository;

import PISI_Wallet.PISI_Wallet.model.Transfer;
import PISI_Wallet.PISI_Wallet.rowMappers.TransferRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransferRepository {
    private final JdbcTemplate jdbcTemplate;

    public TransferRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTransfer(Transfer transfer) {
        String sql = """
            INSERT INTO Transactions 
            (walletId, amount, date, receivingWalletId, adminId,  type) 
            VALUES (?, ?, ?, ?, ?, 'TRANSFER')
            """;

        try {
            // If adminId is 0, set it to NULL
            Integer finalAdminId = (transfer.getAdminId() == 0) ? null : transfer.getAdminId();

            jdbcTemplate.update(sql,
                    transfer.getWalletId(),
                    transfer.getAmount(),
                    transfer.getDate(),
                    transfer.getReceivingWalletId(),
                    finalAdminId
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create transfer: " + e.getMessage(), e);
        }
    }

    public List<Transfer> getTransfersByWalletId(int id) {
        String sql = "SELECT * FROM Transactions WHERE walletId = ? and type = 'TRANSFER'";
        try {
            return jdbcTemplate.query(sql, new Object[]{id}, new TransferRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch transfers: " + e.getMessage(), e);
        }
    }

}