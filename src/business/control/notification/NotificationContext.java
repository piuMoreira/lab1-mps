package business.control.notification;

import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.util.helpers.UserInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationContext {

    private List<Notification> notifications;
    private List<Validator> validator;

    public NotificationContext() {
        this.notifications = new ArrayList<>();
        this.validator = new ArrayList<>();
    }

    public void add(Notification notification) {
        this.notifications.add(notification);
    }

    public void removeAllNotifications() {
        this.notifications.clear();
    }
    
    public void add(Validator validator) {
        this.validator.add(validator);
    }

    public void removeAllVAlidator() {
        this.validator.clear();
    }
    
    public List<Notification> getNotifications() {
		return notifications;
	}

    public void validate(Map<UserInput, String> userInput) {
        for (Validator validator : this.validator) {
        	Notification notif = validator.validate(userInput);
            if(notif != null) {
            	notifications.add(notif);
            }
        }
    }

}
