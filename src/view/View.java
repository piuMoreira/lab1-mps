package view;


import business.control.ControllerFacade;
import view.console.AppConsole;
import view.forms.CreateUserForm;
import view.forms.DeleteUserForm;
import view.menu.*;


public class View {
    private AppConsole appConsole;
    private int state = 0;
    private Menu menu = new MainMenu();
    private ControllerFacade controllerFacade = new ControllerFacade();

    public View() {
        this.appConsole = new AppConsole();
    }

    public void run() throws InterruptedException {
        while (state < 1) {
            appConsole.clear();
            appConsole.write(menu.menuToString());
            menu.parseOption(appConsole.read());
            switch (menu.returnAction()) {
                case Constants
                        .ADD_USER:
                    menu = new AddUserMenu();
                    break;
                case Constants
                        .REMOVE_USER:
                    menu = new DeleteUserMenu();
                    break;
                case Constants.ADD_USER_FORM:
                    appConsole.clear();
                    AddUserFormUI addFormUI = new AddUserFormUI("Add User");
                    CreateUserForm addForm = new CreateUserForm();
                    appConsole.write(addFormUI.getFormHeader());

                    appConsole.write(addFormUI.getFields()[0]);
                    addForm.setEmail(appConsole.read());

                    appConsole.write(addFormUI.getFields()[1]);
                    addForm.setPassword(appConsole.read());

                    // TODO call controller
                    controllerFacade.createUser(addForm.getUserInput());

                    menu = new MainMenu();
                    break;
                case Constants.REMOVE_USER_FORM:
                    appConsole.clear();
                    DeleteUserFormUI removeFormUI = new DeleteUserFormUI("Delete User");
                    DeleteUserForm removeForm = new DeleteUserForm();
                    appConsole.write(removeFormUI.getFormHeader());

                    appConsole.write(removeFormUI.getFields()[0]);
                    removeForm.setEmail(appConsole.read());

                    // TODO call controller
                    controllerFacade.deleteUser(removeForm.getUserInput());

                    menu = new MainMenu();
                    break;

                case Constants.MAIN_MENU:
                    menu = new MainMenu();
                    break;

                case Constants.CLOSE_APP:
                    state = 1;
                    break;
                default:
                    appConsole.write("\n" + "Invalid Option\n");
                    try {
                        appConsole.delay(1);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
            }
        }
        System.out.println("Program Finished.\n************************************************");
    }
}
