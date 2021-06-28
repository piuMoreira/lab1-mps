package view.forms;

import business.util.helpers.UserInput;

public class DeleteNewsForm extends BaseForm{
    public DeleteNewsForm() {
        super();
    }

    public String getNews() {
        return this.userInput.get(UserInput.NEWS);
    }

    public void setNews(String news) {
        this.userInput.put(UserInput.NEWS, news);;
    }
}
