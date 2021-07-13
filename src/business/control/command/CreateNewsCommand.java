package business.control.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.Command;
import business.control.validation.exceptions.CustomException;
import business.model.User;
import business.util.helpers.UserInput;

public class CreateNewsCommand implements Command {

	@Override
	public List<String> execute(Map<UserInput, String> userInput) {
		List<String> errors = new ArrayList<>();

		User user = this.userController.findUserByEmail(userInput);
        errors.addAll(newsController.add(user, userInput));

        return errors;
		
	}

}
