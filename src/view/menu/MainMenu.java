package view.menu;


import java.util.HashMap;
import java.util.Map;

public class MainMenu extends Menu {

    public MainMenu() {
        super();
        this.setOptions(new String[]{
                "1. Add User", "2. Delete User", "3. Add News", "4. Delete News",
                "5. Add Announcements", "6. Delete Announcements", "7. Close Application"
        });
        this.setActionMap(Map.of(
                1, CONSTANTS.ADD_USER_FORM,
                2, CONSTANTS.REMOVE_USER_FORM,
                3, CONSTANTS.ADD_NEWS_FORM,
                4, CONSTANTS.REMOVE_NEWS_FORM,
                5, CONSTANTS.ADD_ANNOUNCEMENTS_FORM,
                6, CONSTANTS.REMOVE_ANNOUNCEMENTS_FORM,
                7, CONSTANTS.CLOSE_APP
        ));
    }

}
