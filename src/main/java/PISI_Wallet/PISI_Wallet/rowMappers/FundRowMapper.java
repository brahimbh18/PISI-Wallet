package PISI_Wallet.PISI_Wallet.rowMappers;

import PISI_Wallet.PISI_Wallet.model.Fund;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class FundRowMapper implements RowMapper<Fund> {

    @Override
    public Fund mapRow(ResultSet rs, int rowNum) throws SQLException {
        Fund fund = new Fund();

        fund.setId(rs.getInt("id"));
        fund.setWalletId(rs.getInt("walletId"));
        fund.setAmount(rs.getDouble("amount"));
        fund.setDate(LocalDateTime.parse(rs.getString("date")));
        fund.setCardId(rs.getInt("cardId"));

        // Handle the adminId which might be NULL in the database
        int adminId = rs.getInt("adminId");
        fund.setAdminId(rs.wasNull() ? 0 : adminId);

        return fund;
    }
}
