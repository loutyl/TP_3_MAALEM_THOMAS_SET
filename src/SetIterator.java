import java.util.Iterator;

public class SetIterator implements Iterator<Integer> {

    private Set set;
    private int currentItemIndex;

    public SetIterator(Set set) {
        this.set = set;
        this.currentItemIndex = 0;
    }

    @Override
    public boolean hasNext() {
        return this.currentItemIndex < this.set.getSize();
    }

    @Override
    public Integer next() {
        this.currentItemIndex++;
        return this.set.getElement(this.currentItemIndex - 1);
    }
}
