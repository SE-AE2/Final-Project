public interface CsvDeserializer<T> {

    T deserialize(String[] line);
}
