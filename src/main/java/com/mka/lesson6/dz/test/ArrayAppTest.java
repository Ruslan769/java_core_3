import com.mka.lesson6.dz.ArrayApp;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArrayAppTest {

    ArrayApp arrayApp = null;

    @Before
    public void init() {
        arrayApp = new ArrayApp();
    }

    @Test
    public void searchNumToArray() {
        int[] expected = new int[]{99, 54, 32};
        int[] actual = arrayApp.searchNumToArray(new Integer[]{23, 15, 34, 4, 99, 54, 32});
        //int[] actual2 = arrayApp.searchNumToArray(new Integer[]{23, 4, 15, 34, 4, 99, 54, 32});
        //int[] actual3 = arrayApp.searchNumToArray(new Integer[]{23, 4, 15, 34, 99, 54, 4, 32});
        //int[] actual4 = arrayApp.searchNumToArray(new Integer[]{23, 15, 34, 99, 54, 32});
        assertArrayEquals(expected, actual);
    }

    @Test
    public void filterArray() {
        boolean actual = arrayApp.filterArray(new Integer[]{1, 4, 1, 1, 4, 1, 4, 1, 4, 4});
        //boolean actual2 = arrayApp.filterArray(new Integer[]{1, 3, 1, 6, 4, 1, 4, 1, 4, 4});
        //boolean actual3 = arrayApp.filterArray(new Integer[]{1, 1, 1, 1});
        assertEquals(true, actual);
    }

    @After
    public void destroy() {
        arrayApp = null;
    }
}