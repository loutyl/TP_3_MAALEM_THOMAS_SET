import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class LinkedListSet implements Iterable<Integer>, Set {

    protected LinkedList<Integer> listData;

    public LinkedListSet(){
        this(Collections.emptyList());
    }

    public LinkedListSet(List<Integer> data){
        this.listData = new LinkedList<>(data);
    }

    public static void main(String[] args) {
        LinkedListSet linkedListSet = new LinkedListSet();

        linkedListSet.add(0);
        linkedListSet.add(1);
        linkedListSet.add(2);
        linkedListSet.add(3);
        linkedListSet.add(4);
        out.println("Ajout d'éléments dans la liste : " + linkedListSet.toString());

        linkedListSet.delete(4);
        out.println("Suppression d'un élément dans la liste : " + linkedListSet.toString());

        out.println();

        out.println("Suppression d'un élément n'existant pas dans la liste :");
        try {
            linkedListSet.delete(6);
        } catch (UnsupportedOperationException exception) {
            out.println("Une exception " + exception.toString() + " a été levée.");
        }

        out.println();

        linkedListSet.setIteratorOrder();
        out.println("Triage de la liste sans spécifier d'ordre : " + linkedListSet.toString());

        linkedListSet.setIteratorOrder(false);
        out.println("Triage décroissant : " + linkedListSet.toString());

        linkedListSet.setIteratorOrder(true);
        out.println("Triage croissant : " + linkedListSet.toString());
    }

    @Override
    public void add(Integer element){
        if (this.contains(element)) {return;}

        this.listData.add(element);
    }

    @Override
    public void delete(Integer element){
        if (!this.contains(element)) { throw new UnsupportedOperationException(); }

        this.listData.remove(element);
    }

    @Override
    public boolean contains(Integer element){
        return this.listData.contains(element);
    }

    @Override
    public int getSize() {
        return this.listData.size();
    }

    @Override
    public int getElement(Integer element) {
        if (!this.contains(element)) {
            throw new IndexOutOfBoundsException();
        }

        return this.listData.get(element);
    }

    @Override
    public Stream getStream() {
        return this.listData.stream();
    }

    @Override
    public void setIteratorOrder(){
        setIteratorOrder(true);
    }

    @Override
    public void setIteratorOrder(boolean ascending){
        Collections.sort(this.listData, ascending
                ? Comparator.<Integer>naturalOrder()
                : Comparator.<Integer>reverseOrder());
    }

    @Override
    public Iterator<Integer> iterator() {
        return new SetIterator(this);
    }

    @Override
    public String toString(){
        return listData.stream().map(Object::toString).collect(Collectors.joining("-"));
    }
}
