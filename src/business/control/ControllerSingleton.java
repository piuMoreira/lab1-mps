package business.control;

import business.control.validation.*;

import java.util.ArrayList;
import java.util.List;

public class ControllerSingleton {

    private ControllerSingleton() {}

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
}
