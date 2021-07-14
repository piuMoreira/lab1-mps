package business.control;

import business.control.validation.*;
import business.control.validation.exceptions.CustomException;
import business.model.User;
import business.util.helpers.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingletonFacade {

    private static SingletonFacade instance = null;

    private SingletonFacade() {}

    public static SingletonFacade getInstance() {
        if (instance == null) {
            instance = new SingletonFacade();
        }

        return instance;
    }

    public static final List<Validator> userValidators = new ArrayList<>() {{
        add(new EmailValidator());
        add(new PasswordValidator());
    }};

    public static final NewsValidator newsValidator = new NewsValidator();

    public static final AnnouncementValidator announcementValidator = new AnnouncementValidator();

    public static final ValidationComposite validationComposite = new ValidationComposite();

    public static final UserController userController = new UserController(validationComposite);
    public static final NewsController newsController = new NewsController(validationComposite);
    public static final AnnouncementController announcementController = new AnnouncementController(validationComposite);


    public static synchronized UserController getUserController() {
        return userController;
    }

    public static synchronized NewsController getNewsController() {
        return newsController;
    }

    public static synchronized AnnouncementController getAnnouncementController() {
        return announcementController;
    }

    public static synchronized List<Validator> getUserValidators() {
        return userValidators;
    }

    public static synchronized NewsValidator getNewsValidator() {
        return newsValidator;
    }

    public static synchronized AnnouncementValidator getAnnouncementValidator() {
        return announcementValidator;
    }

    public static synchronized ValidationComposite getValidationComposite() {
        return validationComposite;
    }

    public List<String> createUser(Map<UserInput, String> userInput) {

        ValidationComposite validationComposite = SingletonFacade.getValidationComposite();

        for (Validator validator: SingletonFacade.getUserValidators()) {
            validationComposite.add(validator);
        }

        UserController userController = SingletonFacade.getUserController();

        List<String> errors = new ArrayList<>();
        errors.addAll(userController.add(userInput));

        validationComposite.removeAll();

        return errors;
    }

    public List<String> deleteUser (Map<UserInput, String> userInput) {
        UserController userController = SingletonFacade.getUserController();
        NewsController newsController = SingletonFacade.getNewsController();
        AnnouncementController announcementController = SingletonFacade.getAnnouncementController();

        ValidationComposite validationComposite = SingletonFacade.getValidationComposite();
        // EmailValidator
        validationComposite.add(SingletonFacade.getUserValidators().get(0));

        List<String> errors = new ArrayList<>();
        errors.addAll( userController.delete(userInput) );
        errors.addAll( newsController.deleteAll(userInput) );
        errors.addAll( announcementController.deleteAll(userInput) );

        validationComposite.removeAll();

        return errors;
    }

    public List<String> createNews(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            ValidationComposite validationComposite = SingletonFacade.getValidationComposite();
            validationComposite.add(SingletonFacade.getNewsValidator());

            User user = SingletonFacade.getUserController().findUserByEmail(userInput);
            errors.addAll(SingletonFacade.getNewsController().add(user, userInput));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        validationComposite.removeAll();

        return errors;
    }

    public List<String> deleteNews(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            ValidationComposite validationComposite = SingletonFacade.getValidationComposite();
            validationComposite.add(SingletonFacade.getNewsValidator());

            User user = SingletonFacade.getUserController().findUserByEmail(userInput);
            SingletonFacade.getNewsController().delete(user, userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        validationComposite.removeAll();

        return errors;
    }

    public List<String> createAnnouncement(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            ValidationComposite validationComposite = SingletonFacade.getValidationComposite();
            validationComposite.add(SingletonFacade.getAnnouncementValidator());

            User user = SingletonFacade.getUserController().findUserByEmail(userInput);
            SingletonFacade.getAnnouncementController().add(user, userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        validationComposite.removeAll();

        return errors;
    }

    public List<String> deleteAnnouncement(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            ValidationComposite validationComposite = SingletonFacade.getValidationComposite();
            validationComposite.add(SingletonFacade.getAnnouncementValidator());

            User user = SingletonFacade.getUserController().findUserByEmail(userInput);
            SingletonFacade.getAnnouncementController().delete(user, userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        validationComposite.removeAll();

        return errors;
    }

    public List<String> undoUserUpdate(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            User user = SingletonFacade.getUserController().findUserByEmail(userInput);
            SingletonFacade.getUserController().undo(user);
        } catch(CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> updateUser(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            ValidationComposite validationComposite = SingletonFacade.getValidationComposite();
            // EmailValidator
            validationComposite.add(SingletonFacade.getUserValidators().get(0));

            User user = SingletonFacade.getUserController().findUserByEmail(userInput);
            SingletonFacade.getUserController().update(user, userInput);
        } catch(CustomException ex) {
            errors.add(ex.getMessage());
        }

        validationComposite.removeAll();

        return errors;
    }

}