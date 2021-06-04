package view.menu;


public class MainMenu extends Menu {

    public MainMenu() {
        super();
        this.setOptions(new String[]{"1. Add User", "2. Delete User", "3. Close Application"});
    }

    @Override
    public String returnAction() {
        switch (this.getAction()) {
            case 1:
                return CONSTANTS.ADD_USER_FORM;
            case 2:
                return CONSTANTS.REMOVE_USER_FORM;
            case 3:
                return CONSTANTS.CLOSE_APP;
            default:
                return CONSTANTS.INVALID_OPTION;
        }
    }

}
