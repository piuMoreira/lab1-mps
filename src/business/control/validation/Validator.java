package business.control.validation;

import business.control.UserInput;

import java.util.List;
import java.util.Map;

public interface Validator {
    public List<String> validate(Map<UserInput, String> value);
}
