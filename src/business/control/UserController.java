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

    public void cadastrarUsuario (Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        for (Validator validator : this.validators) {
            String error = validator.validate(userInput);

            if (error != null) {
                errors.add(error);
            }
        }

        if (errors.size() == 0) {
            new User(userInput.get(UserInput.EMAIL), userInput.get(UserInput.PASSWORD));
        }
    }

}
