package business.control;

import business.control.helpers.UserInput;
import business.model.User;

import java.util.List;
import java.util.Map;

public class ControllerFacade {

    UserController userController = ControllerSingleton.getUserController();
    NewsController newsController = ControllerSingleton.getNewsController();
    AnnouncementController announcementController = ControllerSingleton.getAnnouncementController();

    public List<String> createUser (Map<UserInput, String> userInput) {
        UserController userController = this.userController;
        return userController.add(userInput);
    }

    public List<String> deleteUser (Map<UserInput, String> userInput) {
        UserController userController = this.userController;
        return userController.delete(userInput);
    }

    public List<String> createNews(User user, String title) {
        NewsController newsController = this.newsController;
        return newsController.add(user, title);
    }

    public List<String> deleteNews(User user, String title) {
        NewsController newsController = this.newsController;
        return newsController.delete(user, title);
    }

    public List<String> createAnnouncement(User user, String title) {
        AnnouncementController announcementController = this.announcementController;
        return announcementController.add(user, title);
    }

    public List<String> deleteAnnouncement(User user, String title) {
        AnnouncementController announcementController = this.announcementController;
        return announcementController.delete(user, title);
    }


}
