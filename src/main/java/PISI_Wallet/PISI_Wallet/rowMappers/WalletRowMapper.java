package PISI_Wallet.PISI_Wallet.rowMappers;

import PISI_Wallet.PISI_Wallet.model.Wallet;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class WalletRowMapper implements RowMapper<Wallet> {

    @Override
    public Wallet mapRow(ResultSet rs, int rowNum) throws SQLException {
        Wallet wallet = new Wallet();

        wallet.setId(rs.getInt("id"));
        wallet.setUserId(rs.getInt("userId"));
        wallet.setName(rs.getString("name"));
        wallet.setBalance(rs.getDouble("balance"));
        wallet.setCreatedAt(LocalDateTime.parse(rs.getString("createdAt")));
        wallet.setCode(rs.getString("code"));

        return wallet;
    }
}
