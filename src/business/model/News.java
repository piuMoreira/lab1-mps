package business.model;

public class News {

    private String title;
    private User createdBy;

    public News(User createdBy, String title) {
        this.createdBy = createdBy;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public User getCreatedBy() {
        return this.createdBy;
    }

}
