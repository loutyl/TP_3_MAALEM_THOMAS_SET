public class SetFactory {

    public static Set GetObject(String iterableObject) {
        switch (iterableObject) {
            case "TabSet":
                return new TabSet(0);
            case "LinkedLikedSet":
                return new LinkedListSet();
            default:
                return new LinkedListSet();
        }
    }
}
