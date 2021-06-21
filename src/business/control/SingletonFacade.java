package business.control;

import business.control.validation.*;
import business.util.helpers.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingletonFacade {

    private SingletonFacade() {}

    private static final List<Validator> userValidators = new ArrayList<>() {{
        add(new EmailValidator());
        add(new PasswordValidator());
    }};

    private static final List<Validator> newsValidators = new ArrayList<>() {{
        add(new EmailValidator());
        add(new NewsValidator());
    }};

    private static final List<Validator> announcementValidators = new ArrayList<>() {{
        add(new EmailValidator());
        add(new AnnouncementValidator());
    }};

    private static final UserController userController = new UserController(userValidators);
    private static final NewsController newsController = new NewsController(newsValidators);
    private static final AnnouncementController announcementController = new AnnouncementController(announcementValidators);

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

    public static synchronized List<Validator> getNewsValidators() {
        return newsValidators;
    }

    public static synchronized List<Validator> getAnnouncementValidators() {
        return announcementValidators;
    }

    public List<String> deleteUser (Map<UserInput, String> userInput) {
        UserController userController = SingletonFacade.getUserController();
        NewsController newsController = SingletonFacade.getNewsController();
        AnnouncementController announcementController = SingletonFacade.getAnnouncementController();

        List<String> errors = new ArrayList<>();
        errors.addAll( userController.delete(userInput) );
        errors.addAll( newsController.deleteAll(userInput) );
        errors.addAll( announcementController.deleteAll(userInput) );

        return errors;
    }

}

