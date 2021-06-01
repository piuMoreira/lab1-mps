package view.menu;

public abstract class Menu {
    private String[] options;
    private int action = -1;

    public Menu() {}

    public String[] getOptions() {
        return this.options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public int getAction() {
        return this.action;
    }

    public void setAction(int action) {
        this.action = action;
    }


    public void parseOption(String rawOptionString) {
        try {
            this.setAction(Integer.valueOf(rawOptionString.trim()));
        } catch (NumberFormatException e) {
            this.setAction(-1);
        }

    }

    public String menuToString() {
        String menuOptions = "";
        for (String opt : this.options) {
            menuOptions = menuOptions.concat(opt + "\n");
        }
        return "************************************************\nMENU\n\n" + menuOptions + "\n\nOption: ";
    }

    public abstract String returnAction();
}