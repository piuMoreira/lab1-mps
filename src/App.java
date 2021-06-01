import view.View;

public class App {
    public static void main(String[] args) throws InterruptedException {
        View view = new View();
        try {
            view.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
