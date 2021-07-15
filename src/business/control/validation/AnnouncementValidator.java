package business.control.validation;

import java.util.Map;

import business.control.notification.Notification;
import business.control.validation.exceptions.CustomException;
import business.util.helpers.UserInput;

public class AnnouncementValidator implements Validator {
    @Override
    public Notification validate(Map<UserInput, String> value) {
        String title = value.get(UserInput.ANNOUNCEMENT);

        if (title.length() == 0) {
            return new Notification("Por favor, insira o título do edital");
        }

        if (title.length() > 30) {
            return new Notification("O título do edital deve ter no máximo 15 caracteres");
        }
        return null;
    }
}
