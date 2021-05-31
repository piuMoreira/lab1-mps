package view.menu;

public class DeleteUserMenu extends Menu {
    public DeleteUserMenu() {
        super();
        this.setOptions(new String[]{"1. Fill Delete User Form", "2. Back To Main Menu"});
    }

    @Override
    public String returnAction() {
        switch (this.getAction()) {
            case 1:
                return Constants.REMOVE_USER_FORM;
            case 2:
                return Constants.MAIN_MENU;
            default:
                return Constants.INVALID_OPTION;
        }
    }
}
