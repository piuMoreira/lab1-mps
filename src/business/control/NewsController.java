package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.validation.NewsValidator;
import business.util.helpers.UserInput;
import infra.factory.BinaryWriter;
import infra.factory.NewsBinaryWriter;
import business.control.validation.EmailValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.control.validation.exceptions.FileException;
import business.model.News;
import business.model.User;

public class NewsController {
    private List<Validator> validators;
    private BinaryWriter binaryWriterFactory;
    
    public NewsController(List<Validator> validators) {
        this.validators = validators;
        try {
			binaryWriterFactory = new NewsBinaryWriter();
		} catch (FileException e) {
			e.printStackTrace();
		}
    }

    public List<String> add(User user, Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            for (Validator validator : this.validators) {
                validator.validate(userInput);
            }
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            News news = new News(user, userInput.get(UserInput.NEWS));
            binaryWriterFactory.write(news);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> delete(User user, Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            NewsValidator newsValidator = new NewsValidator();
            newsValidator.validate(userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            binaryWriterFactory.remove(userInput.get(UserInput.NEWS));
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
            NewsBinaryWriter newsBinaryWriter = new NewsBinaryWriter();
            newsBinaryWriter.removeAllNews(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }
}
