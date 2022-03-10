public interface CsvSerializer<T> {

    String serialize(T record);
}
