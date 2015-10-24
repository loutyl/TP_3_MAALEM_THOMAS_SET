import java.util.Iterator;

public class LinkedListSetIterator implements Iterator<Integer> {

    private int currentItemIndex;
    private LinkedListSet listToIterate;

    public LinkedListSetIterator(LinkedListSet listToIterate){
        this.currentItemIndex = 0;
        this.listToIterate = listToIterate;
    }

    @Override
    public boolean hasNext() {
        return this.currentItemIndex < this.listToIterate.listData.size();
    }

    @Override
    public Integer next() {
        this.currentItemIndex++;
        return this.listToIterate.listData.get((this.currentItemIndex - 1));
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
