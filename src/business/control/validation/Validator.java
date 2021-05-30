package business.control.validation;

import java.util.Map;

import business.control.helpers.UserInput;

public interface Validator {
    public void validate(Map<UserInput, String> value) throws Exception;
}
