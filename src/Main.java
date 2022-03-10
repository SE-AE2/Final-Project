import java.util.Scanner;

public class Main {
    User user;
    Scanner scanner;

    public static void main(String[] args) {
        Main main = new Main();
        main.loop();
    }

    public void loop() {
        while (true) {
            scanner = new Scanner(System.in);
            System.out.println("Please input your username:");
            String username = scanner.nextLine();
            System.out.println("Please input your password:");
            String password = scanner.nextLine();
            user = User.authenticate(username, password);
            if (user != null) {
                user.input();
            } else {
                System.out.println("Authenticate failed.");
            }
        }
    }

}
