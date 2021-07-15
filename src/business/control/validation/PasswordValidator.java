package business.control.validation;

import java.util.Map;

import business.control.notification.Notification;
import business.control.validation.exceptions.CustomException;
import business.util.helpers.UserInput;

public class PasswordValidator implements Validator {

    @Override
    public Notification validate(Map<UserInput, String> value) {
        String password = value.get(UserInput.PASSWORD);

        if (password.length() < 8) {
            return new Notification("A senha deve ter no mínimo 8 caracteres");
        }

        if (password.length() > 20) {
            return new Notification("A senha deve ter no máximo 20 caracteres");
        }

        if (password.matches("(?=(.*\\d){2})(?=(.*[A-Za-z]))ˆ.*")) {
            return new Notification("A deve ter letras e no mínimo 2 números");
        }
        
        return null;
    }
}
