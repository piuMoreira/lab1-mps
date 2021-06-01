package business.control.validation;

import java.util.Map;

import business.control.helpers.UserInput;
import business.control.validation.exceptions.CustomException;

public interface Validator {
    public void validate(Map<UserInput, String> value) throws CustomException;
}
