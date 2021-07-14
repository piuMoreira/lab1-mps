package business.control.command;

import business.control.Command;
import business.control.SingletonFacade;
import business.util.helpers.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateUserCommand implements Command {

	@Override
	public List<String> execute(SingletonFacade facade, Map<UserInput, String> userInput) {
        return facade.createUser(userInput);
	}

}
