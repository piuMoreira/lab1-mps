package business.control.command;

import business.control.Command;
import business.util.helpers.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateUserCommand implements Command {

	@Override
	public List<String> execute(Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();
        errors.addAll( this.userController.add(userInput) );

        return errors;
	}

}
