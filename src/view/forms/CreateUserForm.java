package view.forms;
import business.control.helpers.UserInput;

import java.util.HashMap;


public class CreateUserForm extends BaseForm{


    public CreateUserForm() {
        super();
    }

    public String getEmail() {
        return this.userInput.get(UserInput.EMAIL);
    }

    public void setEmail(String email) {
        this.userInput.put(UserInput.EMAIL, email);
    }

    public String getPassword() {
        return this.userInput.get(UserInput.PASSWORD);
    }

    public void setPassword(String password) {
        this.userInput.put(UserInput.PASSWORD, password);;
    }

}
