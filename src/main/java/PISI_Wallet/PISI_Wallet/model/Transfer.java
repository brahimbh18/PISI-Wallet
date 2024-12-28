package PISI_Wallet.PISI_Wallet.model;

public class Transfer extends Transaction {
    int receivingWalletId;

    public Transfer() {}

    public Transfer(int id, int walletId, double amount, int adminId, int receivingWalletId) {
        super(id, walletId, amount, adminId);
        this.receivingWalletId = receivingWalletId;
    }

    public void setReceivingWalletId(int receivingWalletId) {
        this.receivingWalletId = receivingWalletId;
    }

    public int getReceivingWalletId() {
        return receivingWalletId;
    }
}
