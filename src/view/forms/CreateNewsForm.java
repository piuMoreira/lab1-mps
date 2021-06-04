package view.forms;

import business.control.helpers.UserInput;

import java.util.HashMap;

public class CreateNewsForm extends BaseForm{

    public CreateNewsForm() {
        super();
    }

    public String getEmail() {
        return this.userInput.get(UserInput.EMAIL);
    }

    public void setEmail(String email) {
        this.userInput.put(UserInput.EMAIL, email);
    }

    public String getNews() {
        return this.userInput.get(UserInput.NEWS);
    }

    public void setNews(String news) {
        this.userInput.put(UserInput.NEWS, news);;
    }
}
