package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.validation.NewsValidator;
import business.util.helpers.UserInput;
import business.control.validation.EmailValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.model.News;
import business.model.User;

import infra.NewsBinaryWriter;

public class NewsController {
    private List<Validator> validators;
    public NewsController(List<Validator> validators) {
        this.validators = validators;
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
            NewsBinaryWriter newsBinaryWriter = new NewsBinaryWriter();
            newsBinaryWriter.write(news);
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
            NewsBinaryWriter newsBinaryWriter = new NewsBinaryWriter();
            newsBinaryWriter.remove(userInput.get(UserInput.NEWS));
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
            newsBinaryWriter.remove(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }
}
