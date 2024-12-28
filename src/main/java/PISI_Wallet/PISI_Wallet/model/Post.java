package PISI_Wallet.PISI_Wallet.model;

import java.time.LocalDateTime;

public class Post {
    private int id;
    private int adminId;
    private String title;
    private String body;
    private LocalDateTime createdAt;

    public Post() {
        this.createdAt = LocalDateTime.now();
    }

    public Post(int id, int adminId, String title, String body) {
        this.id = id;
        this.adminId = adminId;
        this.title = title;
        this.body = body;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAtAsString() {
        return createdAt.toString();
    }
}
