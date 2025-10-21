import static org.junit.Assert.*;

public class batcodingTest {

    public void firstHalf() {
        assertEquals("woo", new batcoding().firstHalf("WooHoo"));
        assertEquals("alam", new batcoding().firstHalf("alamakota"));
    }

    public void nearHundred() {
        assertTrue(new batcoding().nearHundred(90));
        assertFalse(new batcoding().nearHundred(89));
    }

    public void intMax(){
        assertEquals(10, new batcoding().intMax(1,5,10));
        assertEquals(25, new batcoding().intMax(23,24,25));
    }

    public void helloName(){
        assertEquals("Hello WooHoo!", new batcoding().helloName("WooHoo"));
        assertEquals("Hello Bob!", new batcoding().helloName("Bob"));
    }

    public void makeLast() {
        int[] result1 = {0,0,0,5};
        int[] test1 = {1,5};

        int[] result2 = {1,2,7};
        int[] test2 = {0,0,0,0,0,7};

        assertArrayEquals(result1, new batcoding().makeLast(test1));
        assertArrayEquals(result2, new batcoding().makeLast(test2));

    }

}