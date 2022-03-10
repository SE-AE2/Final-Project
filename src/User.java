import java.util.List;
import java.util.StringJoiner;

public class User {
    private String username;
    private String password;
    private int role;

    public User() {
    }

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void input() {
        System.out.println("Your role is Unknown.");
    }

    public boolean verify(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public static User authenticate(String username, String password) {
        for (User user : getUsers()) {
            if (user.verify(username, password)) {
                if (user.getRole() == 1) return new Administrator(username);
                if (user.getRole() == 2) return new ClassDirector(username);
                if (user.getRole() == 3) return new PTT(username);
                return user;
            }
        }
        return null;
    }

    public static List<User> getUsers() {
        return CsvAccessor.getInstance().findAllUser();
    }

    public String serialize() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(CsvAccessor.SHEET_USER);
        joiner.add(username);
        joiner.add(password);
        joiner.add(String.valueOf(role));
        return joiner.toString();
    }

    public static User deserialize(String[] line) {
        User user = new User();
        user.setUsername(line[1]);
        user.setPassword(line[2]);
        user.setRole(Integer.parseInt(line[3]));
        return user;
    }
}
