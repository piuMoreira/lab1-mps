package business.control.validation;

import java.util.Map;

import business.control.notification.Notification;
import business.control.validation.exceptions.CustomException;
import business.util.helpers.UserInput;

public class EmailValidator implements Validator {

    @Override
    public Notification validate(Map<UserInput, String> value) {
        String email = value.get(UserInput.EMAIL);

        if (email.length() == 0) {
            return new Notification("Por favor, insira seu email");
        }

        if (email.length() > 12) {
            return new Notification("O email deve ter no máximo 12 caracteres");
        }

        if (email.matches(".*\\d")) {
            return new Notification("O email não deve conter números");
        }
        return null;
    }
}
