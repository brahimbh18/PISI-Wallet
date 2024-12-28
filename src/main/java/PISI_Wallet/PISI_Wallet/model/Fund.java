package PISI_Wallet.PISI_Wallet.model;

public class Fund extends Transaction{
    private int cardId;

    public Fund() {}

    public Fund(int id, int walletId, double amount, int adminId, int cardId) {
        super(id, walletId, amount, adminId);
        this.cardId = cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardId() {
        return cardId;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "id=" + getId() +
                ", walletId=" + getWalletId() +
                ", amount=" + getAmount() +
                ", date=" + getDate() +
                ", type='FUND'" +
                '}';
    }
}
