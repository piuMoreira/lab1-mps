package business.control;

import business.control.validation.Validator;
import business.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController {

    private final List<Validator> validators;

    public UserController(List<Validator> validators) {
        this.validators = validators;
    }

    public List<String> cadastrarUsuario (Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        for (Validator validator : this.validators) {
            List<String> errorsValidation = validator.validate(userInput);

            if (errorsValidation.isEmpty()) {
                errors.addAll(errorsValidation);
            }
        }

        if (errors.isEmpty()) {
            new User(userInput.get(UserInput.EMAIL), userInput.get(UserInput.PASSWORD));
            return errors;
        }

        return errors;
    }

}
