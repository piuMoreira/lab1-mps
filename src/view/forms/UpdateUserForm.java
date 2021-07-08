package view.forms;

import business.util.helpers.UserInput;

public class UpdateUserForm extends BaseForm{

    public UpdateUserForm() {
        super();
    }

    public String getEmail() {
        return this.userInput.get(UserInput.EMAIL);
    }

    public void setEmail(String email) {
        this.userInput.put(UserInput.EMAIL, email);
    }

}
