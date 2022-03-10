import java.util.List;
import java.util.Scanner;

public class ClassDirector extends User {
    private Scanner scanner = new Scanner(System.in);

    public ClassDirector(String username) {
        super(username);
    }

    public void addCourse() {
        List<Course> courses = CsvAccessor.getInstance().findAllCourse();
        Course course = new Course();
        System.out.print("input course name: ");
        course.setName(scanner.nextLine());
        System.out.print("input course requirements: ");
        course.setRequirements(scanner.nextLine());
        System.out.print("input course time: ");
        course.setTime(scanner.nextLine());
        System.out.print("input course location: ");
        course.setLocation(scanner.nextLine());
        courses.add(course);
        CsvAccessor.getInstance().saveCourse(courses);
    }

    public void input() {
        System.out.println("Your role is: Class Director.");

        while (true) {
            System.out.println("1. View course list.");
            System.out.println("2. Add course");
            System.out.println("3. Log out");
            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            while (choice > 3 || choice < 1) {
                System.out.println("Incorrect choice, Please try again!");
                System.out.print("Your Choice: ");
                choice = scanner.nextInt();
            }
            switch (choice) {
                case 1:
                    System.out.println("Name, Requirements, Time, Location");
                    viewCourseList();
                    break;
                case 2:
                    addCourse();
                    viewCourseList();
                    break;
                case 3:
                    return;
            }
        }

    }

    public void viewCourseList() {
        CsvAccessor.getInstance().findAllCourse().forEach(System.out::println);
    }
}
