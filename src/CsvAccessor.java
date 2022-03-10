import java.util.List;
import java.util.stream.Collectors;

public class CsvAccessor {

    private static final CsvAccessor instance = new CsvAccessor("ptt.csv");

    public static final String SHEET_COURSE_RECORD = "courseRecord";
    public static final String SHEET_PERSON = "person";
    public static final String SHEET_COURSE = "course";
    public static final String SHEET_USER = "user";

    private final CsvLoader csvLoader;

    public CsvAccessor(String filePath) {
        csvLoader = new CsvLoader(filePath);
    }

    public static CsvAccessor getInstance() {
        return instance;
    }

    public List<Course> findAllCourse() {
        return findAll(SHEET_COURSE, Course::deserialize);
    }

    public List<String> findAllCourseName() {
        return findAllCourse().stream().map(Course::getName).distinct().collect(Collectors.toList());
    }

    public List<User> findAllUser() {
        return findAll(SHEET_USER, User::deserialize);
    }

    public List<Person> findAllPerson() {
        return findAll(SHEET_PERSON, Person::deserialize);
    }

    //public List<CourseList> findAllCourseRecord() {
    //    return findAll(SHEET_COURSE_RECORD, line -> {
    //        CourseList record = new CourseList();
    //        record.setCourse(findCourseByName(line[1]));
    //        List<String> names = new ArrayList<>(Arrays.asList(line).subList(2, line.length));
    //        record.setConfirmPerson(findAllPersonByName(names));
    //        return record;
    //    });
    //}

    public Course findCourseByName(String name) {
        return findAllCourse().stream().filter(course -> course.getName().equals(name)).findFirst().orElse(null);
    }

    public Person findPersonByName(String name) {
        return findAllPerson().stream().filter(person -> person.getName().equals(name)).findFirst().orElse(null);
    }

    public List<Person> findAllPersonByName(List<String> names) {
        return names.stream().map(this::findPersonByName).collect(Collectors.toList());
    }

    public List<String> findAllPersonNames() {
        return findAllPerson().stream().map(Person::getName).collect(Collectors.toList());
    }

    public void saveCourse(List<Course> list) {
        csvLoader.writeLines(SHEET_COURSE, list, Course::serialize);
    }

    public void saveCourse(Course course) {
        csvLoader.writeLine(course, Course::serialize);
    }

    //public void saveUser(List<User> list) {
    //    csvLoader.writeLines(SHEET_USER, list, User::serialize);
    //}
    //
    //public void savePerson(List<Person> list) {
    //    csvLoader.writeLines(SHEET_PERSON, list, Person::serialize);
    //}

    //public void saveCourseRecord(List<CourseList> list) {
    //    csvLoader.writeLines(SHEET_COURSE_RECORD, list, CourseList::serialize);
    //}

    public <T> List<T> findAll(String sheet, CsvDeserializer<T> deserializer) {
        List<String[]> lines = csvLoader.readLines(sheet);
        return lines.stream().map(deserializer::deserialize).collect(Collectors.toList());
    }
}
