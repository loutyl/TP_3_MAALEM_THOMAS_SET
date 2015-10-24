import java.util.stream.Stream;

public interface Set {

    Stream getStream();

    void add(Integer element);

    void delete(Integer element);

    boolean contains(Integer element);

    int getSize();

    int getElement(Integer element);

    String toString();

    void setIteratorOrder();

    void setIteratorOrder(boolean ascending);
}