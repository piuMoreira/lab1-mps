package view.forms;

import business.util.helpers.UserInput;

import java.util.HashMap;

public class DeleteUserForm extends BaseForm{

    public DeleteUserForm() {
        super();
    }

    public String getEmail() {
        return this.userInput.get(UserInput.EMAIL);
    }

    public void setEmail(String email) {
        this.userInput.put(UserInput.EMAIL, email);
    }

}
