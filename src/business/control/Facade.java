package business.control;

import business.util.helpers.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Facade {

    UserController userController = ControllerSingleton.getUserController();
    NewsController newsController = ControllerSingleton.getNewsController();
    AnnouncementController announcementController = ControllerSingleton.getAnnouncementController();

    public List<String> deleteUser (Map<UserInput, String> userInput) {
        UserController userController = this.userController;
        NewsController newsController = this.newsController;
        AnnouncementController announcementController = this.announcementController;

        List<String> errors = new ArrayList<>();
        errors.addAll( userController.delete(userInput) );
        errors.addAll( newsController.deleteAll(userInput) );
        errors.addAll( announcementController.deleteAll(userInput) );

        return errors;
    }

}

