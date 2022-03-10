import java.util.StringJoiner;

public class Course {

    private String name;

    private String requirements;

    private String time;

    private String location;

    private String applyPerson;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApplyPerson() {
        return applyPerson;
    }

    public void setApplyPerson(String applyPerson) {
        this.applyPerson = applyPerson;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", requirements='" + requirements + '\'' +
                ", time=" + time +
                ", location='" + location + '\'' +
                ", applyPerson='" + applyPerson + '\'' +
                '}';
    }

    public String description() {
        return "Course{" +
                "name='" + name + '\'' +
                ", requirements='" + requirements + '\'' +
                ", time=" + time +
                ", location='" + location + '\'' +
                '}';
    }

    protected Course cloneWithApplyPerson(String applyPerson) {
        Course course = new Course();
        course.setName(this.name);
        course.setLocation(this.location);
        course.setTime(this.time);
        course.setRequirements(this.requirements);
        course.setApplyPerson(applyPerson);
        return course;
    }

    public String serialize() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(CsvAccessor.SHEET_COURSE);
        joiner.add(name);
        joiner.add(requirements);
        joiner.add(time);
        joiner.add(location);
        joiner.add(applyPerson);
        return joiner.toString();
    }

    public static Course deserialize(String[] line) {
        Course course = new Course();
        course.setName(line[1]);
        course.setRequirements(line[2]);
        course.setTime(line[3]);
        course.setLocation(line[4]);
        course.setApplyPerson(line[5]);
        return course;
    }
}
