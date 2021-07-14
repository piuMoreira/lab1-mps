package view;


import business.control.Command;
import business.control.SingletonFacade;
import business.control.command.*;
//import business.control.ControllerFacade;
import view.console.AppConsole;
import view.forms.*;
import view.menu.*;


public class View {
    private AppConsole appConsole;
    private SingletonFacade facade;
    private int state = 0;
    private Menu menu = new MainMenu();
    private Command command;

    public View() {
        this.appConsole = new AppConsole();
        this.facade = SingletonFacade.getInstance();
    }

    public void run() throws InterruptedException {
        while (state < 1) {
            appConsole.clear();
            appConsole.write(this.menu.menuToString());
            Integer option = this.menu.parseOption(appConsole.read());
            switch (this.menu.returnAction(option)) {
                case CONSTANTS.ADD_USER_FORM:
                    appConsole.clear();
                    AddUserFormUI addUserFormUI = new AddUserFormUI("Add User");
                    CreateUserForm addUserForm = new CreateUserForm();
                    appConsole.write(addUserFormUI.getFormHeader());

                    appConsole.write(addUserFormUI.getFields()[0]);
                    addUserForm.setEmail(appConsole.read());

                    appConsole.write(addUserFormUI.getFields()[1]);
                    addUserForm.setPassword(appConsole.read());

                    CreateUserCommand command = new CreateUserCommand();
                    command.execute(this.facade, addUserForm.getUserInput());

                    this.menu = new MainMenu();
                    break;
                case CONSTANTS.REMOVE_USER_FORM:
                    appConsole.clear();
                    DeleteUserFormUI removeUserFormUI = new DeleteUserFormUI("Delete User");
                    DeleteUserForm removeUserForm = new DeleteUserForm();
                    appConsole.write(removeUserFormUI.getFormHeader());

                    appConsole.write(removeUserFormUI.getFields()[0]);
                    removeUserForm.setEmail(appConsole.read());

                    DeleteUserCommand deleteUserCommand = new DeleteUserCommand();

                    deleteUserCommand.execute(this.facade, removeUserForm.getUserInput());

                    this.menu = new MainMenu();
                    break;
                case CONSTANTS.UPDATE_USER_FORM:
                    appConsole.clear();
                    UpdateUserFormUI updateUserFormUI = new UpdateUserFormUI("Update User");
                    UpdateUserForm updateUserForm = new UpdateUserForm();
                    appConsole.write(updateUserFormUI.getFormHeader());

                    appConsole.write(updateUserFormUI.getFields()[0]);
                    updateUserForm.setEmail(appConsole.read());

                    UpdateUserCommand updateUserCommand = new UpdateUserCommand();

                    updateUserCommand.execute(this.facade, updateUserForm.getUserInput());

                    this.menu = new MainMenu();
                    break;
                case CONSTANTS.UNDO_USER_UPDATE_FORM:
                    appConsole.clear();
                    UndoUserUpdateFormUI undoUserUpdateFormUI = new UndoUserUpdateFormUI("Update User");
                    UndoUserUpdateForm undoUserUpdateForm = new UndoUserUpdateForm();
                    appConsole.write(undoUserUpdateFormUI.getFormHeader());

                    appConsole.write(undoUserUpdateFormUI.getFields()[0]);
                    undoUserUpdateForm.setEmail(appConsole.read());

                    UndoUserUpdateCommand undoUserUpdateCommand = new UndoUserUpdateCommand();

                    undoUserUpdateCommand.execute(this.facade, undoUserUpdateForm.getUserInput());

                    this.menu = new MainMenu();
                    break;
                case CONSTANTS.ADD_NEWS_FORM:
                    appConsole.clear();
                    AddNewsFormUI addNewsFormUI = new AddNewsFormUI("Add News");
                    CreateNewsForm addNewsForm = new CreateNewsForm();
                    appConsole.write(addNewsFormUI.getFormHeader());

                    appConsole.write(addNewsFormUI.getFields()[0]);
                    addNewsForm.setEmail(appConsole.read());

                    appConsole.write(addNewsFormUI.getFields()[1]);
                    addNewsForm.setNews(appConsole.read());

                    CreateNewsCommand createNewsCommand = new CreateNewsCommand();

                    createNewsCommand.execute(this.facade, addNewsForm.getUserInput());

                    this.menu = new MainMenu();
                    break;
                case CONSTANTS.REMOVE_NEWS_FORM:
                    appConsole.clear();
                    DeleteNewsFormUI removeNewsFormUI = new DeleteNewsFormUI("Delete News");
                    DeleteNewsForm removeNewsForm = new DeleteNewsForm();
                    appConsole.write(removeNewsFormUI.getFormHeader());

                    appConsole.write(removeNewsFormUI.getFields()[0]);
                    removeNewsForm.setNews(appConsole.read());

                    DeleteNewsCommand deleteNewsCommand = new DeleteNewsCommand();

                    deleteNewsCommand.execute(this.facade, removeNewsForm.getUserInput());


                    this.menu = new MainMenu();
                    break;

                case CONSTANTS.ADD_ANNOUNCEMENTS_FORM:
                    appConsole.clear();
                    AddAnnouncementsFormUI addAnnouncementsFormUI = new AddAnnouncementsFormUI("Add Announcements");
                    CreateAnnouncementsForm addAnnouncementsForm = new CreateAnnouncementsForm();
                    appConsole.write(addAnnouncementsFormUI.getFormHeader());

                    appConsole.write(addAnnouncementsFormUI.getFields()[0]);
                    addAnnouncementsForm.setEmail(appConsole.read());

                    appConsole.write(addAnnouncementsFormUI.getFields()[1]);
                    addAnnouncementsForm.setAnnouncement(appConsole.read());

                    CreateAnnouncementCommand createAnnouncementCommand = new CreateAnnouncementCommand();

                    createAnnouncementCommand.execute(this.facade, addAnnouncementsForm.getUserInput());;

                    this.menu = new MainMenu();
                    break;

                case CONSTANTS.REMOVE_ANNOUNCEMENTS_FORM:
                    appConsole.clear();
                    DeleteAnnouncementsFormUI deleteAnnouncementsFormUI = new DeleteAnnouncementsFormUI("Delete Announcements");
                    DeleteAnnouncementsForm deleteAnnouncementsForm = new DeleteAnnouncementsForm();
                    appConsole.write(deleteAnnouncementsFormUI.getFormHeader());

                    appConsole.write(deleteAnnouncementsFormUI.getFields()[0]);
                    deleteAnnouncementsForm.setAnnouncement(appConsole.read());

                    DeleteAnnouncementCommand deleteAnnouncementCommand = new DeleteAnnouncementCommand();

                    deleteAnnouncementCommand.execute(this.facade, deleteAnnouncementsForm.getUserInput());

                    this.menu = new MainMenu();
                    break;

                case CONSTANTS.MAIN_MENU:
                    this.menu = new MainMenu();
                    break;

                case CONSTANTS.CLOSE_APP:
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
