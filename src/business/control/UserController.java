package business.control;

import business.control.validation.Validator;
import business.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController {

    private final List<Validator> validators;
    private final List<User> users;

    public UserController(List<Validator> validators) {
        this.validators = validators;
        this.users = new ArrayList<>();
    }

    public List<String> add (Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        for (Validator validator : this.validators) {
            List<String> errorsValidation = validator.validate(userInput);

            if (errorsValidation.isEmpty()) {
                errors.addAll(errorsValidation);
            }
        }

        if (errors.isEmpty()) {
            User user = new User(userInput.get(UserInput.EMAIL), userInput.get(UserInput.PASSWORD));
            this.users.add(user);

            //TODO: chamar classe da infra passando a lista de usuários

            return errors;
        }

        return errors;
    }

    public void delete (String email) {
        List<String> errors = new ArrayList<>();

        //TODO: Verificar se o email existe na base de dados


    }

}
