package business.model;

public class User {
    private final String email;
    private final String password;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
        this.email = "";
        this.password = "";
    }

    public String getEmail() {
        return email;
    }
}
