import java.util.List;
import java.util.StringJoiner;

/**
 * Unreasonable and should be discarded
 */
public class CourseList {

    private Course course;

    private List<Person> confirmPerson;

    public boolean matchCourseName(String courseName) {
        return course.getName().equals(courseName);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Person> getConfirmPerson() {
        return confirmPerson;
    }

    public void setConfirmPerson(List<Person> confirmPerson) {
        this.confirmPerson = confirmPerson;
    }

    public String serialize() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(CsvAccessor.SHEET_COURSE_RECORD);
        joiner.add(course.getName());
        for (Person person : confirmPerson) {
            joiner.add(person.getName());
        }
        return joiner.toString();
    }
}
