package PISI_Wallet.PISI_Wallet.model;

public class Payment extends Transaction {
    private String service;

    public Payment() {}

    public Payment(int id, int walletId, double amount, int adminId, String service) {
        super(id, walletId, amount, adminId);
        this.service = service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getService() {
        return service;
    }
}
