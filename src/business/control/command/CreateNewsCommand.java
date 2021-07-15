package business.control.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.Command;
import business.control.SingletonFacade;
import business.control.notification.Notification;
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.control.validation.exceptions.InexistentUserException;
import business.model.User;
import business.util.helpers.UserInput;

public class CreateNewsCommand implements Command {

	SingletonFacade facade;
	Map<UserInput, String> userInput;

	public CreateNewsCommand(SingletonFacade facade, Map<UserInput, String> userInput) {
		this.facade = facade;
		this.userInput = userInput;
	}

	@Override
	public List<Notification> execute() {
		return this.facade.createNews(this.userInput);
	}

}
