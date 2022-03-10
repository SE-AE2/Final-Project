import java.util.List;
import java.util.Scanner;

public class Administrator extends User {
    private final Scanner input = new Scanner(System.in);

    private List<String> courseNames;

    private List<String> personNames;

    public Administrator(String username) {
        super(username);
    }

    public void input() {
        System.out.println("Your role is: Administrator.");

        courseNames = CsvAccessor.getInstance().findAllCourseName();
        personNames = CsvAccessor.getInstance().findAllPersonNames();
        System.out.println("course name: " + Tools.formatNames(courseNames));
        System.out.print("input course name: ");
        int courseNo = input.nextInt();
        System.out.println("apply name: " + Tools.formatNames(personNames));
        System.out.print("input confirm name (split with \",\"): ");
        String confirmNo = input.next();

        confirmPerson(courseNo, confirmNo);
    }

    private void confirmPerson(int courseNo, String confirmNo) {
        Course selectedCourse = CsvAccessor.getInstance().findCourseByName(courseNames.get(courseNo - 1));
        String[] confirmPersonNo = confirmNo.split(",");
        for (String no : confirmPersonNo) {
            String personName = personNames.get(Integer.parseInt(no) - 1);
            Course newCourse = selectedCourse.cloneWithApplyPerson(personName);
            CsvAccessor.getInstance().saveCourse(newCourse);
        }

        System.out.println("You have confirmed to join.");
    }
}
