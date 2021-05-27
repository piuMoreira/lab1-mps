package business.control.validation;

import business.control.UserInput;

import java.util.Map;

public interface Validator {
    public String validate(Map<UserInput, String> value);
}
