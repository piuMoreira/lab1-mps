package business.control;

import business.control.helpers.UserInput;
import business.control.validation.EmailValidator;
import business.control.validation.Validator;
import business.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserController {

    private List<Validator> validators;
    private List<User> users;

    public UserController(List<Validator> validators) {
        this.validators = validators;
        this.users = new ArrayList<>();
    }

    public List<String> add (Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        try {
            for (Validator validator : this.validators) {
                validator.validate(userInput);
            }

            User user = new User(userInput.get(UserInput.EMAIL), userInput.get(UserInput.PASSWORD));
            this.users.add(user);

            //TODO: chamar classe da infra passando a lista de usuários
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> delete (Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            EmailValidator emailValidator = new EmailValidator();
            emailValidator.validate(userInput);

            //TODO: Passar o email para o infra para deletar (infra deve retornar erro caso o email não exista na base de dados)
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

}
