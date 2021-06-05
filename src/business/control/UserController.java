package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.util.helpers.UserInput;
import business.control.validation.EmailValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.model.User;
import infra.UserBinaryWriter;

public class UserController {

    private List<Validator> validators;
    private List<User> users;
    //TODO: inicializar a classe UserBinaryWriter

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
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            User user = new User(userInput.get(UserInput.EMAIL), userInput.get(UserInput.PASSWORD));
            this.users.add(user);

            binaryWriter.writeUserList(this.users);
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
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            //TODO: chamar o findUserByEmail antes de remover
            binaryWriter.removeUser(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

}
