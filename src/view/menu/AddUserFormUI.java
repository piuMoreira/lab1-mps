package view.menu;

public class AddUserFormUI extends FormUI{
    public AddUserFormUI(String formName) {
        super(formName);
        setFields(new String[]{"email: ", "password: "});
    }
}
