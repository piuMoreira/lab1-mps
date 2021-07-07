package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.validation.AnnouncementValidator;
import business.control.validation.EmailValidator;
import business.control.validation.NewsValidator;
import business.control.validation.PasswordValidator;
import business.control.validation.Validator;
import business.util.helpers.UserInput;

public interface Command {
	// EXECUTE
	public List<String> execute(Map<UserInput, String> userInput);
	
	public static final List<Validator> userValidators = new ArrayList<>() {{
        add(new EmailValidator());
        add(new PasswordValidator());
    }};

    public static final List<Validator> newsValidators = new ArrayList<>() {{
        add(new NewsValidator());
    }};

    public static final List<Validator> announcementValidators = new ArrayList<>() {{
        add(new AnnouncementValidator());
    }};

    public static final UserController userController = new UserController(userValidators);
    public static final NewsController newsController = new NewsController(newsValidators);
    public static final AnnouncementController announcementController = new AnnouncementController(announcementValidators);

}
