package view.menu;

public class AddNewsFormUI extends FormUI{
    public AddNewsFormUI(String formName) {
        super(formName);
        setFields(new String[]{"email: ", "title: "});
    }
}
