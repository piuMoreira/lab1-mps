package business.control;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

import business.control.memento.UserCaretaker;
import business.control.validation.ValidationComposite;
import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentUserException;
import business.util.helpers.UserInput;
import business.control.validation.EmailValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.model.User;
import infra.DataAccess;
import infra.factory.BinaryWriter;
import infra.factory.UserBinaryWriter;

public class UserController {

    private ValidationComposite validation;
    private List<User> users;
    private BinaryWriter binaryWriterFactory;

    public UserController(ValidationComposite validation) {
        this.validation = validation;
        this.users = new ArrayList<>();
        try {
			binaryWriterFactory = new UserBinaryWriter();
		} catch (FileException e) {
			e.printStackTrace();
		}
    }

    public List<String> add (Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        try {
            this.validation.validate(userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            User user = new User(userInput.get(UserInput.NAME), userInput.get(UserInput.EMAIL), userInput.get(UserInput.PASSWORD));
            this.users.add(user);

            binaryWriterFactory.write(this.users);
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
            binaryWriterFactory.remove(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public User findUserByEmail (Map<UserInput, String> userInput) throws FileException, InexistentUserException {
        DataAccess dataAccess = new DataAccess();

        return dataAccess.findUserByEmail(userInput.get(UserInput.EMAIL));
    }

    public List<String> update (User user, Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            EmailValidator emailValidator = new EmailValidator();
            emailValidator.validate(userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            UserCaretaker userCaretaker = new UserCaretaker(user);
            userCaretaker.backup();

            binaryWriterFactory.update(user.getName(), userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> undo (User user) {
        List<String> errors = new ArrayList<>();

        try {
            UserCaretaker userCaretaker = new UserCaretaker(user);
            userCaretaker.undo();

            binaryWriterFactory.update(user.getName(), user.getEmail());
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

}
