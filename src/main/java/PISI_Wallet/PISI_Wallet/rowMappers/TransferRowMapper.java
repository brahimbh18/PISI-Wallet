package PISI_Wallet.PISI_Wallet.rowMappers;

import PISI_Wallet.PISI_Wallet.model.Transfer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class TransferRowMapper implements RowMapper<Transfer> {

    @Override
    public Transfer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transfer transfer = new Transfer();

        transfer.setId(rs.getInt("id"));
        transfer.setWalletId(rs.getInt("walletId"));
        transfer.setAmount(rs.getDouble("amount"));
        transfer.setDate(LocalDateTime.parse(rs.getString("date")));
        transfer.setReceivingWalletId(rs.getInt("receivingWalletId"));

        // Handle the adminId which might be NULL in the database
        int adminId = rs.getInt("adminId");
        transfer.setAdminId(rs.wasNull() ? 0 : adminId);

        return transfer;
    }
}
