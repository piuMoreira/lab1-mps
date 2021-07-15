package business.control.validation;

import java.util.Map;

import business.control.notification.Notification;
import business.control.validation.exceptions.CustomException;
import business.util.helpers.UserInput;

public class NewsValidator implements Validator {

    @Override
    public Notification validate(Map<UserInput, String> value) {
        String title = value.get(UserInput.NEWS);

        if (title.length() == 0) {
            return new Notification("Por favor, insira o título da notícia");
        }

        if (title.length() > 30) {
            return new Notification("O título da notícia deve ter no máximo 30 caracteres");
        }

        if (title.matches(".*\\d")) {
            return new Notification("O título da notícia não deve conter números");
        }
        
        return null;
    }

}
