package business.control.validation;

import business.control.UserInput;

import java.util.Map;

public class EmailValidator implements Validator {

    @Override
    public String validate(Map<UserInput, String> value) {
        String email = value.get(UserInput.EMAIL);
        return "";
    }
}
