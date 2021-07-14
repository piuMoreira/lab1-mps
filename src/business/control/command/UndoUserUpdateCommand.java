package business.control.command;

import business.control.Command;
import business.control.SingletonFacade;
import business.control.validation.exceptions.CustomException;
import business.model.User;
import business.util.helpers.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UndoUserUpdateCommand implements Command {

    @Override
    public List<String> execute(SingletonFacade facade, Map<UserInput, String> userInput) {
        return facade.undoUserUpdate(userInput);
    }

}