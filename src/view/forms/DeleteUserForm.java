package view.forms;

import business.util.helpers.UserInput;

import java.util.Map;

public class DeleteUserForm {
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

}
