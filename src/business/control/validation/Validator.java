package business.control.validation;

import java.util.List;
import java.util.Map;

import business.util.helpers.UserInput;
import business.control.notification.Notification;
import business.control.validation.exceptions.CustomException;

public interface Validator {
    public Notification validate(Map<UserInput, String> value);
}
