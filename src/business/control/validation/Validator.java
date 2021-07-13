package business.control.validation;

import java.util.List;
import java.util.Map;

import business.util.helpers.UserInput;
import business.control.validation.exceptions.CustomException;

public interface Validator {
    public void validate(Map<UserInput, String> value) throws CustomException;
}
