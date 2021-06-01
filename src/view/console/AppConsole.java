package view.console;

import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class AppConsole implements IApplicationConsole {
    private Console console;
    private Scanner scanner;

    public AppConsole() {
        this.console = System.console();
        this.scanner = new Scanner(console.reader());
    }

    @Override
    public String read() {
        return this.scanner.nextLine();
    }

    @Override
    public void write(String content) {
        System.out.print(content);
    }

    public void flush() {
        this.console.flush();
    }

    public void clear() {
        this.console.writer().write("\033[H\033[2J");
        this.flush();
    }

    public void delay(long seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }
}
