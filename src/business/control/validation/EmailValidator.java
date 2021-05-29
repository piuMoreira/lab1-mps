package business.control.validation;

import business.control.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailValidator implements Validator {

    @Override
    public void validate(Map<UserInput, String> value) {
        String email = value.get(UserInput.EMAIL);

        if (email.length() == 0) {
            throw new MissingParam("Por favor, insira seu email");
        }

        if (email.length() > 12) {
            throw new InvalidParam("O email deve ter no máximo 12 caracteres");
        }

        if (email.matches(".*\\d")) {
            throw new InvalidParam("O email não deve conter números");
        }
    }
}
