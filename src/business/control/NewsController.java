package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.util.helpers.UserInput;
import business.control.validation.EmailValidator;
import business.control.validation.Validator;
import business.control.validation.exceptions.CustomException;
import business.model.News;
import business.model.User;

import infra.binaryWriter;

public class NewsController {
    private List<Validator> validators;
    //TODO: inicializar a classe NewsBinaryWriter e DataAccess
    public NewsController(List<Validator> validators) {
        this.validators = validators;
    }

    public List<String> add(Map<UserInput, String> userInput) {
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
            News news = new News(user, userInput.get(UserInput.NEWS));
            binaryWriter.writeNews(news);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }

    public List<String> delete(Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            NewsValidator newsValidator = new NewsValidator();
            newsValidator.validate(userInput);
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        try {
            // No infra tem que chamar o findNewsByTitle, se não encontrar lançar exceção
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
            // No infra tem que chamar o findNewsByTitle, se não encontrar lançar exceção
            binaryWriter.removeAllNews(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }
}
