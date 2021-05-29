package business.control.validation;

import business.control.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmailValidator implements Validator {

    @Override
    public List<String> validate(Map<UserInput, String> value) {
        String email = value.get(UserInput.EMAIL);

        List<String> errorValidation = new ArrayList<>();

        if (email.length() == 0) {
            errorValidation.add("Por favor, insira seu email");
        }

        if (email.length() > 12) {
            errorValidation.add("O email deve ter no máximo 12 caracteres");
        }

        if (email.matches(".*\\d")) {
            errorValidation.add("O email não deve conter números");
        }

        return errorValidation;
    }
}
