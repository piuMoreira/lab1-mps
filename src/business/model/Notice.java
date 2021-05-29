package business.model;

import java.util.Date;

public class Notice {

    private User createdBy;
    private String title;
    private Date createdAt;

    public Notice(User createdBy, String title, Date createdAt) {
        this.createdBy = createdBy;
        this.title = title;
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return this.title;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
