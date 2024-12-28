package PISI_Wallet.PISI_Wallet.model;

import javax.print.attribute.standard.MediaSize;

public class BankCard {
    private int id;
    private String cardNumber;
    private String cardHolder;
    private String expirationDate;
    private int cvv;

    public BankCard() {}

    public BankCard(int id, String cardNumber, String cardHolder, String expirationDate, int cvv) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    @Override
    public String toString() {
        return "BankCard{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolder + '\'' +
                ", expirationDate=" + expirationDate +
                ", cvv=" + cvv +
                '}';
    }
}
