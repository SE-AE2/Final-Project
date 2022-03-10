import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {
    private String filePath;
    private String encoding;

    public CsvLoader(String csvFilePath) {
        this(csvFilePath, "utf-8");
    }

    public CsvLoader(String csvFilePath, String encoding) {
        this.filePath = csvFilePath;
        this.encoding = encoding;
    }

    public List<String[]> readLines(String sheet) {
        List<String[]> lines = new ArrayList<>();
        try {
            DataInputStream fin = new DataInputStream(new FileInputStream(filePath));
            BufferedReader br = new BufferedReader(new InputStreamReader(fin, encoding));

            String line;
            while ((line = br.readLine()) != null) {
                String[] attribute = line.split(",");
                if (attribute[0].equals(sheet)) {
                    lines.add(attribute);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public <T> void writeLines(String sheet, List<T> list, CsvSerializer<T> serializer) {
        try {
            LineNumberReader reader = new LineNumberReader(new FileReader(filePath, Charset.forName(encoding)));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(sheet)) {
                    lines.add(line);
                }
            }
            list.forEach(t -> lines.add(serializer.serialize(t)));
            reader.close();

            PrintWriter writer = new PrintWriter(new FileWriter(filePath, Charset.forName(encoding)));
            lines.forEach(writer::println);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public <T> void writeLine(T entity, CsvSerializer<T> serializer) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(filePath, Charset.forName(encoding), true));
            writer.println(serializer.serialize(entity));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
