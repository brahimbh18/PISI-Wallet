package PISI_Wallet.PISI_Wallet.model;

import java.time.LocalDateTime;

public class Transaction {
    private int id;
    private int walletId;
    private double amount;
    private LocalDateTime date;
    private int adminId;


    public Transaction() {
        this.date = LocalDateTime.now();
    }

    public Transaction(int id, int walletId, double amount, int adminId) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
        this.date = LocalDateTime.now();
        this.adminId = adminId;
    }

    // Getters and setters for common fields
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }
}
