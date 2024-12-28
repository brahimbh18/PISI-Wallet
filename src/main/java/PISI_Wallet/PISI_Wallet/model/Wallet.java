package PISI_Wallet.PISI_Wallet.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Wallet {
    private int id;
    private int userId;
    private String name;
    private double balance;
    private LocalDateTime createdAt;
    private String code;

    public Wallet() {
        this.createdAt = LocalDateTime.now();
    }

    public Wallet(int id, int userId, String name, double balance, String createdAt, String code) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUserId(int userCin) {
        this.userId = userCin;
    }

    public int getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getCreatedAtAsString() {
        return createdAt.toString();  // If you need to get the date as a string for storage or display
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", userId=" + userId +
                ", balance=" + balance +
                ", createdAt=" + createdAt +
                ", code='" + code + '\'' +
                '}';
    }
}
