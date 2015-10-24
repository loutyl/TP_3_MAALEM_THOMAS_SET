import java.util.Iterator;


public class TabSetIterator implements Iterator<Integer> {

    private TabSet tabToIterate;
    private int currentItemIndex;

    public TabSetIterator(TabSet tabToIterate){
        this.currentItemIndex = 0;
        this.tabToIterate = tabToIterate;
    }

    @Override
    public boolean hasNext() {
        return this.currentItemIndex < this.tabToIterate.tabData.length;
    }

    @Override
    public Integer next() {
        this.currentItemIndex++;
        return this.tabToIterate.tabData[(this.currentItemIndex - 1)];
    }
}

