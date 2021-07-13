package business.control.validation;

import business.util.helpers.UserInput;
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.InvalidParamException;
import business.control.validation.exceptions.MissingParamException;

import java.util.List;
import java.util.Map;

public class AnnouncementValidator implements Validator {
    @Override
    public List<String> validate(Map<UserInput, String> value) throws CustomException {
        String title = value.get(UserInput.ANNOUNCEMENT);

        if (title.length() == 0) {
            throw new MissingParamException("Por favor, insira o título do edital");
        }

        if (title.length() > 30) {
            throw new InvalidParamException("O título do edital deve ter no máximo 15 caracteres");
        }

        return null;
    }
}
