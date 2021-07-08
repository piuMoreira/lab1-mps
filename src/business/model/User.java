package business.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import business.control.memento.Memento;
import business.control.memento.ConcreteMemento;


public class User {
    private String name;
    private String email;
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User() {
        this.name = "";
        this.email = "";
        this.password = "";
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Memento save() {
        LocalDateTime date = LocalDateTime.now();
        return new ConcreteMemento(
                date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                date,
                this.email
        );
    }

    public void restore(Memento memento) {
        this.email = memento.getEmail();
    }
}
