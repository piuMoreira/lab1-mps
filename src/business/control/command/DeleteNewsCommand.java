package business.control.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.Command;
import business.control.SingletonFacade;
import business.control.validation.exceptions.CustomException;
import business.model.User;
import business.util.helpers.UserInput;

public class DeleteNewsCommand implements Command {

	@Override
	public List<String> execute(SingletonFacade facade, Map<UserInput, String> userInput) {
        return facade.deleteNews(userInput);
	}

}
