package PISI_Wallet.PISI_Wallet.repository;

import PISI_Wallet.PISI_Wallet.model.Payment;
import PISI_Wallet.PISI_Wallet.rowMappers.PaymentRowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PaymentRepository {
    private final JdbcTemplate jdbcTemplate;

    public PaymentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createPayment(Payment payment) {
        String sql = """
            INSERT INTO Transactions 
            (walletId, amount, date, service, adminId, type) 
            VALUES (?, ?, ?, ?, ?, 'PAYMENT')
            """;

        try {
            // If adminId is 0, set it to NULL
            Integer finalAdminId = (payment.getAdminId() == 0) ? null : payment.getAdminId();

            jdbcTemplate.update(sql, 
                payment.getWalletId(),
                payment.getAmount(),
                payment.getDate(),
                payment.getService(),
                    finalAdminId
            );
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to create payment: " + e.getMessage(), e);
        }
    }


    public List<Payment> getPaymentsByWalletId(int id) {
        String sql = "SELECT * FROM Transactions WHERE walletId = ? and type = 'PAYMENT'";
        try {
            return jdbcTemplate.query(sql, new Object[]{id}, new PaymentRowMapper());
        } catch (DataAccessException e) {
            throw new RuntimeException("Failed to fetch payments: " + e.getMessage(), e);
        }
    }
}
