package PISI_Wallet.PISI_Wallet.rowMappers;

import PISI_Wallet.PISI_Wallet.model.BankCard;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BankCardRowMapper implements RowMapper<BankCard> {

    @Override
    public BankCard mapRow(ResultSet rs, int rowNum) throws SQLException {
        BankCard bankCard = new BankCard();

        bankCard.setId(rs.getInt("id"));
        bankCard.setCardNumber(rs.getString("cardNumber"));
        bankCard.setCardHolder(rs.getString("cardHolder"));
        bankCard.setExpirationDate(rs.getString("expirationDate"));
        bankCard.setCvv(rs.getInt("cvv"));

        return bankCard;
    }
}
