package business.control.validation;

import business.control.helpers.UserInput;

import java.util.Map;

public interface Validator {
    public void validate(Map<UserInput, String> value);
}
