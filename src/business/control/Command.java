package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.validation.*;
import business.util.helpers.UserInput;

public interface Command {
	// EXECUTE
	public List<String> execute(Map<UserInput, String> userInput);
	
	public static final List<Validator> userValidators = new ArrayList<>() {{
        add(new EmailValidator());
        add(new PasswordValidator());
    }};

	ValidationComposite userValidationComposite = new ValidationComposite(userValidators);

    public static final List<Validator> newsValidators = new ArrayList<>() {{
        add(new NewsValidator());
    }};

    ValidationComposite newsValidationComposite = new ValidationComposite(newsValidators);

    public static final List<Validator> announcementValidators = new ArrayList<>() {{
        add(new AnnouncementValidator());
    }};

    ValidationComposite announcementValidationComposite = new ValidationComposite(announcementValidators);

    public static final UserController userController = new UserController(userValidationComposite);
    public static final NewsController newsController = new NewsController(newsValidationComposite);
    public static final AnnouncementController announcementController = new AnnouncementController(announcementValidationComposite);

}
