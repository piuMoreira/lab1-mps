package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.validation.NewsValidator;
import business.util.helpers.UserInput;
import infra.factory.BinaryWriter;
import infra.factory.NewsBinaryWriter;
import business.control.notification.Notification;
import business.control.notification.NotificationContext;
import business.control.validation.EmailValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.model.News;
import business.model.User;

public class NewsController {
    private NotificationContext validation;
    private BinaryWriter binaryWriterFactory;
    
    public NewsController(NotificationContext validation) {
        this.validation = validation;
        try {
			binaryWriterFactory = new NewsBinaryWriter();
		} catch (FileException e) {
			e.printStackTrace();
		}
    }

    public List<Notification> add(User user, Map<UserInput, String> userInput) {

        this.validation.validate(userInput);

        try {
            News news = new News(user, userInput.get(UserInput.NEWS));
            binaryWriterFactory.write(news);
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }

    public List<Notification> delete(User user, Map<UserInput, String> userInput) {

        this.validation.validate(userInput);

        try {
            binaryWriterFactory.remove(userInput.get(UserInput.NEWS));
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }

    public List<Notification> deleteAll(Map<UserInput, String> userInput) {

        this.validation.validate(userInput);

        try {
            NewsBinaryWriter newsBinaryWriter = new NewsBinaryWriter();
            newsBinaryWriter.removeAll(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }
}
