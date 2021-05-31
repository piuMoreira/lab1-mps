package view.forms;
import business.control.helpers.UserInput;

import java.util.Map;

public class CreateUserForm {

    private Map<UserInput, String> userInput;

    public Map<UserInput, String> getUserInput() {
        return userInput;
    }

    public String getEmail() {
        return userInput.get(UserInput.EMAIL);
    }

    public void setEmail(String email) {
        this.userInput.put(UserInput.EMAIL, email);
    }

    public String getPassword() {
        return userInput.get(UserInput.PASSWORD);
    }

    public void setPassword(String password) {
        this.userInput.put(UserInput.PASSWORD, password);;
    }
}
