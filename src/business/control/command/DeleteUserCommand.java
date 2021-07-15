package business.control.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.Command;
import business.control.SingletonFacade;
import business.control.notification.Notification;
import business.util.helpers.UserInput;

public class DeleteUserCommand implements Command {

	SingletonFacade facade;
	Map<UserInput, String> userInput;

	public DeleteUserCommand(SingletonFacade facade, Map<UserInput, String> userInput) {
		this.facade = facade;
		this.userInput = userInput;
	}

	@Override
	public List<Notification> execute() {
        return this.facade.deleteUser(this.userInput);
	}

}
