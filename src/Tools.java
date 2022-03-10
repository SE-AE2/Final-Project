import java.util.List;
import java.util.StringJoiner;

public class Tools {

    public static String formatNames(List<String> names) {
        StringJoiner joiner = new StringJoiner(",");
        for (int i = 0; i < names.size(); i++) {
            joiner.add(1 + i + "." + names.get(i));
        }
        return joiner.toString();
    }
}
