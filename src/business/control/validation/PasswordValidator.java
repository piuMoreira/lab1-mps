package business.control.validation;

import business.control.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PasswordValidator implements Validator {

    @Override
    public void validate(Map<UserInput, String> value) {
        String password = value.get(UserInput.PASSWORD);

        List<String> errorValidation = new ArrayList<>();

        if (password.length() < 8) {
            throw new InvalidParam("A senha deve ter no mínimo 8 caracteres");
        }

        if (password.length() > 20) {
            throw new InvalidParam("A senha deve ter no máximo 20 caracteres");
        }

        if (password.matches("(?=(.*\\d){2})(?=(.*[A-Za-z]))ˆ.*")) {
            throw new InvalidParam("A deve ter letras e no mínimo 2 números");
        }
    }
}
