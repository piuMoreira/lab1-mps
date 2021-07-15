package business.control;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Map;

import business.control.memento.UserCaretaker;
import business.control.notification.Notification;
import business.control.notification.NotificationContext;
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

    private NotificationContext validation;
    private List<User> users;
    private BinaryWriter binaryWriterFactory;

    public UserController(NotificationContext validation) {
        this.validation = validation;
        this.users = new ArrayList<>();
        try {
			binaryWriterFactory = new UserBinaryWriter();
		} catch (FileException e) {
			e.printStackTrace();
		}
    }

    public List<Notification> add (Map<UserInput, String> userInput) {


        this.validation.validate(userInput);

        try {
            User user = new User(userInput.get(UserInput.NAME), userInput.get(UserInput.EMAIL), userInput.get(UserInput.PASSWORD));
            this.users.add(user);

            binaryWriterFactory.write(this.users);
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }

    public List<Notification> delete (Map<UserInput, String> userInput) {

        this.validation.validate(userInput);

        try {
            binaryWriterFactory.remove(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }

    public User findUserByEmail (Map<UserInput, String> userInput) throws FileException, InexistentUserException {
        DataAccess dataAccess = new DataAccess();

        return dataAccess.findUserByEmail(userInput.get(UserInput.EMAIL));
    }

    public List<Notification> update (User user, Map<UserInput, String> userInput) {

        this.validation.validate(userInput);

        try {
            UserCaretaker userCaretaker = new UserCaretaker(user);
            userCaretaker.backup();

            binaryWriterFactory.update(user.getName(), userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }

    public List<Notification> undo (User user) {

        try {
            UserCaretaker userCaretaker = new UserCaretaker(user);
            userCaretaker.undo();

            binaryWriterFactory.update(user.getName(), user.getEmail());
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }

}
