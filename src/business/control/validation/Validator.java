package business.control.validation;

import business.control.UserInput;

import java.util.List;
import java.util.Map;

public interface Validator {
    public void validate(Map<UserInput, String> value);
}
