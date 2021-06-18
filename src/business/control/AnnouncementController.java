package business.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import business.control.validation.EmailValidator;
import business.util.helpers.UserInput;
import business.control.validation.AnnouncementValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.model.Announcement;
import business.model.User;
import infra.binaryWriter;

public class AnnouncementController {

    private List<Validator> validators;
    //TODO: inicializar a classe BinaryWriter (ou a classe que vai cuidar do announcement na infra)

    public AnnouncementController(List<Validator> validators) {
        this.validators = validators;
    }

    public List<String> add (Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        try {
            for (Validator validator : this.validators) {
                validator.validate(userInput);
            }
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            User user = binaryWriter.findUserByEmail(userInput.get(UserInput.EMAIL));
            Announcement announcement = new Announcement(user, userInput.get(UserInput.ANNOUNCEMENT), new Date());
            binaryWriter.writeNews(announcement);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> delete (Map<UserInput, String> userInput) {

        List<String> errors = new ArrayList<>();

        try {
            AnnouncementValidator announcementValidator = new AnnouncementValidator();
            announcementValidator.validate(userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            //TODO: No infra tem que chamar o findNewsByTitle, se não encontrar lançar exceção
            binaryWriter.removeNews(userInput.get(UserInput.NEWS));
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
            binaryWriter.removeAllNews(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }
}
