package business.model;

import java.util.Date;

import business.control.validation.exceptions.CustomException;

public class Announcement {

    private User createdBy;
    private String title;
    private Date createdAt;

    public Announcement(User createdBy, String title, Date createdAt) throws CustomException {
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
