package view.menu;

public class DeleteNewsFormUI extends FormUI{
    public DeleteNewsFormUI(String formName) {
        super(formName);
        setFields(new String[]{"email: ", "title: "});
    }
}
