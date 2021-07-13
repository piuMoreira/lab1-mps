package business.control.validation;

import business.util.helpers.UserInput;
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.InvalidParamException;
import business.control.validation.exceptions.MissingParamException;

import java.util.List;
import java.util.Map;

public class NewsValidator implements Validator {

    @Override
    public void validate(Map<UserInput, String> value) throws CustomException {
        String title = value.get(UserInput.NEWS);

        if (title.length() == 0) {
            throw new MissingParamException("Por favor, insira o título da notícia");
        }

        if (title.length() > 30) {
            throw new InvalidParamException("O título da notícia deve ter no máximo 30 caracteres");
        }

        if (title.matches(".*\\d")) {
            throw new InvalidParamException("O título da notícia não deve conter números");
        }
    }

}
