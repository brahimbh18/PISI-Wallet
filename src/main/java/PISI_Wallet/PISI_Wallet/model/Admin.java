package PISI_Wallet.PISI_Wallet.model;

import java.time.LocalDateTime;

public class Admin extends User{
    private String role;
    private LocalDateTime hireDate;

    public Admin() {
        this.hireDate = LocalDateTime.now();
    }

    public Admin(int id,int cin, String firstName, String lastName, String dateOfBirth, String placeOfBirth, String gender, String phoneNumber, String email, String password, String role) {
        super(id, cin, firstName, lastName, dateOfBirth, placeOfBirth, gender, phoneNumber, email, password);
        this.role = role;
        this.hireDate = LocalDateTime.now();
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDateTime getHireDate() {
        return hireDate;
    }
}
