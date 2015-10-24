import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Objects;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SetTests {

    private static Set setToTest;

    @Before
    public void setUp() {
        setToTest = SetFactory.GetObject("TabSet");
    }

    @Test
    public void testAddMethod(){
        setToTest.add(4);
        assertTrue(setToTest.getStream().filter(value -> Objects.equals(value, 4)).findAny().isPresent());
    }

    @Test(expected = UnsupportedOperationException.class )
    public void testDeleteMethod() {
        setToTest.delete(3);
        assertFalse(setToTest.getStream().filter(value -> Objects.equals(value, 3)).findAny().isPresent());

        setToTest.delete(6); //Throws UnsupportedOperationException
    }

    @Test
    public void testContainsMethod(){
        setToTest.add(5);
        assertTrue(setToTest.contains(5));
        assertFalse(setToTest.contains(6));
    }

    @Test
    public void testSortAndDisplayMethods() {
        setToTest.add(0);
        setToTest.add(1);
        setToTest.add(2);
        setToTest.add(3);

        assertEquals("0-1-2-3", setToTest.toString());

        setToTest.setIteratorOrder();
        assertEquals("0-1-2-3", setToTest.toString());

        setToTest.setIteratorOrder(false);
        assertEquals("3-2-1-0", setToTest.toString());

        setToTest.setIteratorOrder(true);
        assertEquals("0-1-2-3", setToTest.toString());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetElementMethod() {
        setToTest.add(1);
        assertEquals(1, setToTest.getElement(1));

        setToTest.getElement(2); //Throws UnsupportedOperationException
    }

    @Test
    public void testCompactAndGetSizeMethods() {
        setToTest.add(1);
        assertEquals(1, setToTest.getSize());

        if (setToTest instanceof TabSet) {
            setToTest.add(2);
            assertTrue(setToTest.getSize() != 2);
            ((TabSet) setToTest).compact();
            assertTrue(setToTest.getSize() == 2);
        } else {
            setToTest.add(2);
            assertTrue(setToTest.getSize() == 2);
        }
    }
}