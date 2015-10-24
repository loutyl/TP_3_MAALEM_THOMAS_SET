import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class TabSet implements Iterable<Integer>, Set {

    protected Integer[] tabData;
    private Integer lastElementUsedIndex;

    public TabSet(int sizeTab){
        this.tabData = new Integer[sizeTab];
        this.lastElementUsedIndex = 0;
    }

    public static void main(String[] args) {
        TabSet tabSet = new TabSet(0);

        tabSet.add(0);
        tabSet.add(1);
        tabSet.add(2);
        tabSet.add(3);
        tabSet.add(4);
        out.println("Ajout d'éléments dans le tableau : " + tabSet.toString());

        tabSet.delete(4);
        out.println("Suppression d'un élément dans le tableau : " + tabSet.toString());

        out.println();

        out.println("Suppression d'un élément n'existant pas dans le tableau :");
        try {
            tabSet.delete(6);
        } catch (UnsupportedOperationException exception) {
            out.println("Une exception " + exception.toString() + " a été levée.");
        }

        out.println();

        tabSet.setIteratorOrder();
        out.println("Triage du tableau sans spécifier d'ordre : " + tabSet.toString());

        tabSet.setIteratorOrder(false);
        out.println("Triage décroissant : " + tabSet.toString());

        tabSet.setIteratorOrder(true);
        out.println("Triage croissant : " + tabSet.toString());
    }

    @Override
    public void add(Integer element){
        if (this.contains(element)) {return;}

        if (this.lastElementUsedIndex == this.tabData.length){
            this.tabData = Arrays.copyOf(this.tabData, (int)(this.tabData.length + (1 + this.tabData.length) * 1.5));
        }

        this.tabData[this.lastElementUsedIndex] = element;
        this.lastElementUsedIndex += 1;
        this.setIteratorOrder();
    }

    @Override
    public void delete(Integer element) {
        if (!this.contains((element))) { throw new UnsupportedOperationException(); }

        this.tabData = Stream.of(this.tabData).filter(value -> !Objects.equals(value, element))
                .collect(Collectors.toList()).toArray(new Integer[(this.tabData.length - 2)]);
        this.lastElementUsedIndex -= 1;
    }

    @Override
    public boolean contains(Integer element){
        return Arrays.binarySearch(this.tabData, 0, this.lastElementUsedIndex,  element) >= 0;
    }

    @Override
    public int getSize() {
        return this.tabData.length;
    }

    @Override
    public int getElement(Integer element) {
        if (!this.contains(element)) {
            throw new IndexOutOfBoundsException();
        }

        return this.tabData[element];
    }

    @Override
    public String toString(){
        return Stream.of(this.tabData).filter(value -> !Objects.equals(value, null)).map(Object::toString).collect(Collectors.joining("-"));
    }

    public void compact(){
        if (this.tabData.length == this.lastElementUsedIndex){ return ;}

        this.tabData = Arrays.copyOf(this.tabData, this.lastElementUsedIndex);
    }

    @Override
    public void setIteratorOrder(){
        this.setIteratorOrder(true);
    }

    @Override
    public void setIteratorOrder(boolean ascending){
        Arrays.sort(this.tabData, 0, this.lastElementUsedIndex, ascending
                ? Comparator.<Integer>naturalOrder()
                : Comparator.<Integer>reverseOrder());
    }

    @Override
    public Stream getStream() {
        return Stream.of(this.tabData);
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SetIterator(this);
    }
}
