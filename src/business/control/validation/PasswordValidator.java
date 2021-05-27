package business.control.validation;

import business.control.UserInput;

import java.util.Map;

public class PasswordValidator implements Validator {

    @Override
    public String validate(Map<UserInput, String> value) {
        String password = value.get(UserInput.PASSWORD);
        return "";
    }
}
