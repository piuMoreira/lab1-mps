package business.control.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.Command;
import business.util.helpers.UserInput;

public class DeleteUserCommand implements Command {

	@Override
	public List<String> execute(Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();
        errors.addAll( userController.delete(userInput) );
        errors.addAll( newsController.deleteAll(userInput) );
        errors.addAll( announcementController.deleteAll(userInput) );

        return errors;
	}

}
