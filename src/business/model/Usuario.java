package business.model;

public class Usuario {
    private final String email;
    private final String password;

    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }
}
