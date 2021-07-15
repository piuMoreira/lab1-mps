package business.control.command;

import business.control.Command;
import business.control.SingletonFacade;
import business.control.notification.Notification;
import business.util.helpers.UserInput;

import java.util.List;
import java.util.Map;

public class UpdateUserCommand implements Command {

    SingletonFacade facade;
    Map<UserInput, String> userInput;

    public UpdateUserCommand(SingletonFacade facade, Map<UserInput, String> userInput) {
        this.facade = facade;
        this.userInput = userInput;
    }

    @Override
    public List<Notification> execute() {
        return this.facade.updateUser(this.userInput);
    }

}