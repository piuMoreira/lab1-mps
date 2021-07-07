package business.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import business.control.validation.EmailValidator;
import business.util.helpers.UserInput;
import infra.factory.AnnouncementBinaryWriter;
import infra.factory.BinaryWriter;
import business.control.validation.AnnouncementValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.model.Announcement;
import business.model.User;

public class AnnouncementController {

    private List<Validator> validators;
    private BinaryWriter binaryWriterFactory;

    public AnnouncementController(List<Validator> validators) {
        this.validators = validators;
        try {
			binaryWriterFactory = new AnnouncementBinaryWriter();
		} catch (FileException e) {
			e.printStackTrace();
		}
    }

    public List<String> add (User user, Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        try {
            for (Validator validator : this.validators) {
                validator.validate(userInput);
            }
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {            
            Announcement announcement = new Announcement(user, userInput.get(UserInput.ANNOUNCEMENT), new Date());
            binaryWriterFactory.write(announcement);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> delete (User user, Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        try {
            AnnouncementValidator announcementValidator = new AnnouncementValidator();
            announcementValidator.validate(userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            binaryWriterFactory.remove(userInput.get(UserInput.ANNOUNCEMENT));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> deleteAll(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            EmailValidator emailValidator = new EmailValidator();
            emailValidator.validate(userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            AnnouncementBinaryWriter announcementBinaryWriter = new AnnouncementBinaryWriter();
//            announcementBinaryWriter.removeAllAnnouncements(userInput.get(UserInput.ANNOUNCEMENT));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }
}
