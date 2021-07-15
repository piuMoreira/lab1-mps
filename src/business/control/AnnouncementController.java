package business.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import business.control.validation.EmailValidator;
import business.util.helpers.UserInput;
import infra.factory.AnnouncementBinaryWriter;
import infra.factory.BinaryWriter;
import business.control.notification.Notification;
import business.control.notification.NotificationContext;
import business.control.validation.AnnouncementValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.model.Announcement;
import business.model.User;

public class AnnouncementController {

    private NotificationContext validation;
    private BinaryWriter binaryWriterFactory;

    public AnnouncementController(NotificationContext validation) {
        this.validation = validation;
        try {
			binaryWriterFactory = new AnnouncementBinaryWriter();
		} catch (FileException e) {
			e.printStackTrace();
		}
    }

    public List<Notification> add (User user, Map<UserInput, String> userInput) {


        this.validation.validate(userInput);

        try {            
            Announcement announcement = new Announcement(user, userInput.get(UserInput.ANNOUNCEMENT), new Date());
            binaryWriterFactory.write(announcement);
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }

    public List<Notification> delete (User user, Map<UserInput, String> userInput) {


        this.validation.validate(userInput);

        try {
            binaryWriterFactory.remove(userInput.get(UserInput.ANNOUNCEMENT));
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }

    public List<Notification> deleteAll(Map<UserInput, String> userInput) {

        this.validation.validate(userInput);

        try {
            AnnouncementBinaryWriter announcementBinaryWriter = new AnnouncementBinaryWriter();
            // TODO: criar função para remover todos os announcements de um user.
//            announcementBinaryWriter.removeAllAnnouncements(userInput.get(UserInput.ANNOUNCEMENT));
        } catch (CustomException ex) {
        }

        return validation.getNotifications();
    }
}
