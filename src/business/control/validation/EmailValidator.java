package business.control.validation;

import java.util.Map;

import business.control.helpers.UserInput;
import business.control.validation.exceptions.InvalidParamException;
import business.control.validation.exceptions.MissingParamException;

public class EmailValidator implements Validator {

    @Override
    public void validate(Map<UserInput, String> value) throws Exception {
        String email = value.get(UserInput.EMAIL);

        if (email.length() == 0) {
            throw new MissingParamException("Por favor, insira seu email");
        }

        if (email.length() > 12) {
            throw new InvalidParamException("O email deve ter no máximo 12 caracteres");
        }

        if (email.matches(".*\\d")) {
            throw new InvalidParamException("O email não deve conter números");
        }
    }
}
