package business.control.validation;

import business.control.validation.exceptions.CustomException;
import business.util.helpers.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidationComposite implements Validator {

    private ArrayList<Validator> validators = new ArrayList<>();

    public ValidationComposite(ArrayList<Validator> validators) {
        this.validators = validators;
    }

    public  List<String> validate(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            for (Validator validator : this.validators) {
                validator.validate(userInput);
            }
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

}
