package business.control.validation;

import business.control.UserInput;

import java.util.Map;

public class PasswordValidator implements Validator {

    @Override
    public void validate(Map<UserInput, String> value) throws CustomException {
        String password = value.get(UserInput.PASSWORD);

        if (password.length() < 8) {
            throw new InvalidParamException("A senha deve ter no mínimo 8 caracteres");
        }

        if (password.length() > 20) {
            throw new InvalidParamException("A senha deve ter no máximo 20 caracteres");
        }

        if (password.matches("(?=(.*\\d){2})(?=(.*[A-Za-z]))ˆ.*")) {
            throw new InvalidParamException("A deve ter letras e no mínimo 2 números");
        }
    }
}
