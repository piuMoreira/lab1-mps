package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.validation.*;
import business.util.helpers.UserInput;

public interface Command {
	List<String> execute();
}
