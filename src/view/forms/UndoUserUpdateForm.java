package view.forms;

import business.util.helpers.UserInput;

public class UndoUserUpdateForm extends BaseForm{

    public UndoUserUpdateForm() {
        super();
    }

    public String getEmail() {
        return this.userInput.get(UserInput.EMAIL);
    }

    public void setEmail(String email) {
        this.userInput.put(UserInput.EMAIL, email);
    }

}
