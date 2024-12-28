package PISI_Wallet.PISI_Wallet.rowMappers;

import PISI_Wallet.PISI_Wallet.model.Payment;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class PaymentRowMapper implements RowMapper<Payment> {

    @Override
    public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Payment payment = new Payment();
        
        payment.setId(rs.getInt("id"));
        payment.setWalletId(rs.getInt("walletId"));
        payment.setAmount(rs.getDouble("amount"));
        payment.setDate(LocalDateTime.parse(rs.getString("date")));
        payment.setService(rs.getString("service"));
        
        // Handle the adminId which might be NULL in the database
        int adminId = rs.getInt("adminId");
        payment.setAdminId(rs.wasNull() ? 0 : adminId);
        
        return payment;
    }
} 