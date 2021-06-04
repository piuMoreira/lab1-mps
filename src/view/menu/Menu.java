package view.menu;


import java.util.Map;

public abstract class Menu {
    private String[] options;
    private Map<Integer, String> actionMap;

    public Menu() {}

    public String[] getOptions() {
        return this.options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public Integer parseOption(String rawOptionString) {
        try {
            return Integer.valueOf(rawOptionString.trim());
        } catch (NumberFormatException e) {
            return -1;
        }

    }

    public String menuToString() {
        String menuOptions = "";
        for (String opt : this.options) {
            menuOptions = menuOptions.concat(opt + "\n");
        }
        return "************************************************\nMENU\n\n" + menuOptions + "\n\nOption: ";
    }

    public void setActionMap(Map<Integer, String> actMap) {
        this.actionMap = actMap;
    };

    public String getAction(Integer act) {
        return this.actionMap.get(act);
    }

    public String returnAction(Integer option){
        return this.getAction(option);
    }
}
