package business.control.command;

import business.control.Command;
import business.util.helpers.UserInput;

import java.util.List;
import java.util.Map;

public class Executor {

    public List<String> execute(Command command) {
        return command.execute();
    }

}
