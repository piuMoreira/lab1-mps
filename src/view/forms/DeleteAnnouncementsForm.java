package view.forms;

import business.control.helpers.UserInput;

public class DeleteAnnouncementsForm extends BaseForm{
    public DeleteAnnouncementsForm() {
        super();
    }

    public String getAnnouncement() {
        return this.userInput.get(UserInput.ANNOUNCEMENT);
    }

    public void setAnnouncement(String news) {
        this.userInput.put(UserInput.ANNOUNCEMENT, news);;
    }
}
