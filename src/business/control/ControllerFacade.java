package business.control;

import business.util.helpers.UserInput;

import java.util.HashMap;
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

        Map<UserInput, String> userInput = new HashMap<UserInput, String>() {{
            put(UserInput.EMAIL, userEmail);
            put(UserInput.NEWS, title);
        }};

        return newsController.add(userInput);
    }

    public List<String> deleteNews(String title) {
        NewsController newsController = this.newsController;

        Map<UserInput, String> userInput = new HashMap<UserInput, String>() {{
            put(UserInput.NEWS, title);
        }};

        return newsController.delete(userInput);
    }

    public List<String> createAnnouncement(String userEmail, String title) {
        AnnouncementController announcementController = this.announcementController;

        Map<UserInput, String> userInput = new HashMap<UserInput, String>() {{
            put(UserInput.EMAIL, userEmail);
            put(UserInput.ANNOUNCEMENT, title);
        }};

        return announcementController.add(userInput);
    }

    public List<String> deleteAnnouncement(String title) {
        AnnouncementController announcementController = this.announcementController;

        Map<UserInput, String> userInput = new HashMap<UserInput, String>() {{
            put(UserInput.ANNOUNCEMENT, title);
        }};

        return announcementController.delete(userInput);
    }


}
