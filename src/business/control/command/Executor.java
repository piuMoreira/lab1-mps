package business.control.command;

import business.control.Command;
import business.control.notification.Notification;
import business.util.helpers.UserInput;

import java.util.List;
import java.util.Map;

public class Executor {

    public List<Notification> execute(Command command) {
        return command.execute();
    }

}
