package view.forms;

import business.control.helpers.UserInput;

import java.util.HashMap;

public class BaseForm {
    protected HashMap<UserInput, String> userInput;

    public BaseForm() {
        this.userInput = new HashMap<>();
    }

    public HashMap<UserInput, String> getUserInput() {
        return userInput;
    }
}
