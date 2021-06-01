package view.menu;

public class AddUserMenu extends Menu{
    public AddUserMenu() {
        super();
        this.setOptions(new String[]{"1. Fill Add User Form", "2. Back To Main Menu"});
    }

    @Override
    public String returnAction() {
        switch (this.getAction()) {
            case 1:
                return Constants.ADD_USER_FORM;
            case 2:
                return Constants.MAIN_MENU;
            default:
                return Constants.INVALID_OPTION;
        }
    }
}
