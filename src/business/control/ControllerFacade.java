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

    public List<String> createNews(String userEmail, String title) {
        NewsController newsController = this.newsController;
        return newsController.add(userEmail, title);
    }

    public List<String> deleteNews(String userEmail, String title) {
        NewsController newsController = this.newsController;
        return newsController.delete(userEmail, title);
    }

    public List<String> createAnnouncement(String userEmail, String title) {
        AnnouncementController announcementController = this.announcementController;
        return announcementController.add(userEmail, title);
    }

    public List<String> deleteAnnouncement(String userEmail, String title) {
        AnnouncementController announcementController = this.announcementController;
        return announcementController.delete(userEmail, title);
    }


}
