package business.control.validation;

import business.control.validation.exceptions.CustomException;
import business.util.helpers.UserInput;

import java.util.List;
import java.util.Map;

public class ValidationComposite implements Validator {

    private List<Validator> validators;

    public ValidationComposite(List<Validator> validators) {
        this.validators = validators;
    }

    public void validate(Map<UserInput, String> userInput) throws CustomException {
        try {
            for (Validator validator : this.validators) {
                validator.validate(userInput);
            }
        } catch (CustomException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

}
