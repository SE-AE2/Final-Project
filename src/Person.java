import java.util.StringJoiner;

public class Person {

    private String name;

    private String major;

    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name=\'" + name + '\'' +
                ", major='" + major + '\'' +
                ", age=" + age +
                '}';
    }

    public String serialize() {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(CsvAccessor.SHEET_PERSON);
        joiner.add(name);
        joiner.add(major);
        joiner.add(String.valueOf(age));
        return joiner.toString();
    }

    public static Person deserialize(String[] line) {
        Person person = new Person();
        person.setName(line[1]);
        person.setMajor(line[2]);
        person.setAge(Integer.parseInt(line[3]));
        return person;
    }
}
