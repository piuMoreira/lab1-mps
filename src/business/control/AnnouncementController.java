package business.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import business.control.validation.EmailValidator;
import business.control.validation.ValidationComposite;
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

    private ValidationComposite validation;
    private BinaryWriter binaryWriterFactory;

    public AnnouncementController(ValidationComposite validation) {
        this.validation = validation;
        try {
			binaryWriterFactory = new AnnouncementBinaryWriter();
		} catch (FileException e) {
			e.printStackTrace();
		}
    }

    public List<String> add (User user, Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        try {
            this.validation.validate(userInput);
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
            this.validation.validate(userInput);
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
            // TODO: criar função para remover todos os announcements de um user.
//            announcementBinaryWriter.removeAllAnnouncements(userInput.get(UserInput.ANNOUNCEMENT));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }
}
