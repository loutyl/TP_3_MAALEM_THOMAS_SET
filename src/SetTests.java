import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class SetTests {

    private static LinkedListSet linkedListSetToTest;
    private static TabSet arraySetToTest;

    @BeforeClass
    public static void oneTimeSetUp() {
        linkedListSetToTest = new LinkedListSet();
        arraySetToTest = new TabSet(new Integer[]{0,1,2,3});
    }

    @Before
    public void setUp() {
        linkedListSetToTest = new LinkedListSet(Arrays.asList(0,3,1,2));
        arraySetToTest = new TabSet(new Integer[]{0,1,2,3});
    }

    @After
    public void tearDown(){
        linkedListSetToTest.listData.clear();
        arraySetToTest.tabData = null;
    }

    @Test
    public void testAddMethod(){
        linkedListSetToTest.add(4);
        assertTrue(linkedListSetToTest.listData.stream().filter(value -> value == 4).findAny().isPresent());

        arraySetToTest.add(4);
        assertTrue(Stream.of(arraySetToTest.tabData).filter(value -> value == 4).findAny().isPresent());
    }

    @Test(expected = UnsupportedOperationException.class )
    public void testLinkedListDeleteMethod(){
        linkedListSetToTest.delete(3);
        assertFalse(linkedListSetToTest.listData.stream().filter(value -> value == 3).findAny().isPresent());

        linkedListSetToTest.delete(6); //Throws UnsupportedOperationException
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testTabSetDeleteMethod(){ arraySetToTest.delete(6); }

    @Test
    public void testContainsMethod(){
        linkedListSetToTest.add(5);
        assertTrue(linkedListSetToTest.contains(5));
        assertFalse(linkedListSetToTest.contains(6));

        arraySetToTest.add(5);
        assertTrue(arraySetToTest.contains(5));
        assertFalse(arraySetToTest.contains(6));
    }

    @Test
    public void testSortAndDisplayMethod(){
        assertEquals("0-3-1-2", linkedListSetToTest.toString());

        linkedListSetToTest.setIteratorOrder();
        assertEquals("0-1-2-3", linkedListSetToTest.toString());

        linkedListSetToTest.setIteratorOrder(false);
        assertEquals("3-2-1-0", linkedListSetToTest.toString());

        linkedListSetToTest.setIteratorOrder(true);
        assertEquals("0-1-2-3", linkedListSetToTest.toString());
    }

    @Test
    public void testTabSetSortAndDisplayMethod(){
        assertEquals("0-1-2-3", arraySetToTest.toString());

        arraySetToTest.setIteratorOrder();
        assertEquals("0-1-2-3", arraySetToTest.toString());

        arraySetToTest.setIteratorOrder(false);
        assertEquals("3-2-1-0", arraySetToTest.toString());

        arraySetToTest.setIteratorOrder(true);
        assertEquals("0-1-2-3", arraySetToTest.toString());
    }

    @Test
    public void testTabSetArraySize(){
        arraySetToTest = new TabSet(0);
        assertTrue(arraySetToTest.tabData.length == 0);

        arraySetToTest = new TabSet(3);
        assertTrue(arraySetToTest.tabData.length == 3);

        arraySetToTest = new TabSet(0);
        arraySetToTest.add(6);
        arraySetToTest.add(7);
        assertTrue(arraySetToTest.tabData.length != 2);

        arraySetToTest = new TabSet(0);
        int lastIndexAdded = 10;
        for (int i = 0 ; i <= lastIndexAdded; i++){
            arraySetToTest.add(i);
            assertTrue(arraySetToTest.tabData.length != 0 && arraySetToTest.tabData.length != lastIndexAdded);
        }

        arraySetToTest.compact();
        assertTrue(arraySetToTest.tabData.length == (lastIndexAdded + 1)); //Array length = 11
    }
}