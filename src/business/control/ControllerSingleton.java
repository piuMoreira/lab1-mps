package business.control;

import business.control.validation.EmailValidator;
import business.control.validation.PasswordValidator;
import business.control.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class ControllerSingleton {

    private ControllerSingleton() {}

    private static final List<Validator> validators = new ArrayList<>() {{
        add(new EmailValidator());
        add(new PasswordValidator());
    }};

    private static final UserController userController = new UserController(validators);
    private static final NewsController newsController = new NewsController();
    private static final AnnouncementController announcementController = new AnnouncementController();

    public static synchronized UserController getUserController() {
        return userController;
    }

    public static synchronized NewsController getNewsController() {
        return newsController;
    }

    public static synchronized AnnouncementController getAnnouncementController() {
        return announcementController;
    }

    public static synchronized List<Validator> getValidators() {
        return validators;
    }

}
