package view.forms;

import business.control.helpers.UserInput;

public class CreateAnnouncementsForm extends BaseForm{
    public CreateAnnouncementsForm() {
        super();
    }

    public String getEmail() {
        return this.userInput.get(UserInput.EMAIL);
    }

    public void setEmail(String email) {
        this.userInput.put(UserInput.EMAIL, email);
    }

    public String getAnnouncement() {
        return this.userInput.get(UserInput.ANNOUNCEMENT);
    }

    public void setAnnouncement(String news) {
        this.userInput.put(UserInput.ANNOUNCEMENT, news);;
    }
}
