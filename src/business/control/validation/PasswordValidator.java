package business.control.validation;

import business.control.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordValidator implements Validator {

    @Override
    public List<String> validate(Map<UserInput, String> value) {
        String password = value.get(UserInput.PASSWORD);

        List<String> errorValidation = new ArrayList<>();

        if (password.length() < 8) {
            errorValidation.add("A senha deve ter no mínimo 8 caracteres");
        }

        if (password.length() > 20) {
            errorValidation.add("A senha deve ter no máximo 20 caracteres");
        }

        if (password.matches("(?=(.*\\d){2})(?=(.*[A-Za-z]))ˆ.*")) {
            errorValidation.add("A deve ter letras e no mínimo 2 números");
        }

        return errorValidation;
    }
}
