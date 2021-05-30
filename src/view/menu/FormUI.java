package view.menu;

public class FormUI {

    private String formName;
    private String[] fields;

    public FormUI(String formName) {
        this.formName = formName;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    public String getFormHeader() {
        return "************************************************\n" + this.formName + " Form\n\n";
    }
}
