import java.util.List;
import java.util.Scanner;

public class PTT extends User {
    private Scanner input = new Scanner(System.in);

    public PTT(String username) {
        super(username);
    }

    public void input() {
        System.out.println("Your role is: PTT.");

        while (true) {
            List<String> courseNames = CsvAccessor.getInstance().findAllCourseName();
            System.out.println("course name: " + Tools.formatNames(courseNames));
            System.out.println("input course name: ");
            int courseNo = input.nextInt();
            Course selectedCourse = CsvAccessor.getInstance().findCourseByName(courseNames.get(courseNo - 1));
            System.out.println("Course description:" + selectedCourse.description());

            System.out.println("Do you want to join 1.Y,2.N : ");
            int joinFlag = input.nextInt();

            if (joinFlag == 1) {
                System.out.println("You join this course");
                Course newCourse = selectedCourse.cloneWithApplyPerson(getUsername());
                CsvAccessor.getInstance().saveCourse(newCourse);
            } else {
                System.out.println("You could not join this course");
            }
        }
    }
}
