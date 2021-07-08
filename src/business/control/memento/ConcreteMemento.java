package business.control.memento;

import java.time.LocalDateTime;

public class ConcreteMemento implements Memento{

    private String name;
    private LocalDateTime date;
    private String email;

    public ConcreteMemento(
        String name,
        LocalDateTime date,
        String email
    ) {
        this.name = name;
        this.date = date;
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public String getEmail() {
        return this.email;
    }
}
