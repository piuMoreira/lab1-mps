package business.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import business.control.validation.NewsValidator;
import business.control.validation.ValidationComposite;
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
    private ValidationComposite validation;
    private BinaryWriter binaryWriterFactory;
    
    public NewsController(ValidationComposite validation) {
        this.validation = validation;
        try {
			binaryWriterFactory = new NewsBinaryWriter();
		} catch (FileException e) {
			e.printStackTrace();
		}
    }

    public List<String> add(User user, Map<UserInput, String> userInput) {
        List<String> errors = new ArrayList<>();

        try {
            this.validation.validate(userInput);
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
            this.validation.validate(userInput);
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
            // TODO: criar função para remover todas as news de um usuário.
//            newsBinaryWriter.removeAllNews(userInput.get(UserInput.EMAIL));
        } catch (CustomException ex) {
            errors.add(ex.getMessage());
        }

        return errors;
    }
}
