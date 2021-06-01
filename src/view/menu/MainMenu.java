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
                return Constants.ADD_USER;
            case 2:
                return Constants.REMOVE_USER;
            case 3:
                return Constants.CLOSE_APP;
            default:
                return Constants.INVALID_OPTION;
        }
    }

}
