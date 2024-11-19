package Test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import Recursion.Recursion;
import Recursion.Search;

public class TestRecursion {
    Recursion recursion = new Recursion();
    Search search = new Search();

    @Test
    public void TestHCF() {

        assertEquals(1, recursion.HCF(4, 5));
        assertEquals(2, recursion.HCF(14, 26));

    }

    // Negetive tests for Hcf and LCM
    @Test
    public void negativeTest() {

        AssertionError e1 = assertThrows(AssertionError.class, () -> {
            recursion.HCF(0, -1);
        });
        assertEquals("Invalid input(inputs should be greater than 0)", e1.getMessage());
        AssertionError e2 = assertThrows(AssertionError.class, () -> {
            recursion.LCM(0, -1);
        });
        assertEquals("Invalid input(inputs should be greater than 0)", e2.getMessage());
    }

    @Test
    public void TestLCM() {
        assertEquals(28, recursion.LCM(4, 28));
        assertEquals(12, recursion.LCM(3, 4));
    }

    @Test
    public void TestLinearSearch() {
        int[] testinput = { 1, 2, 5, 3, 7 };
        assertEquals(3, search.linearSearch(testinput, 0, 3));
        assertEquals(-1, search.linearSearch(testinput, 0, 21));

    }

    @Test
    public void TestBinarySearch() {
        int[] testinput = { 1, 2, 3, 5, 7 };
        assertEquals(2, search.binarySearch(testinput, 3));
        assertEquals(-1, search.binarySearch(testinput, 21));

    }

    @Test
    public void TestnQueen() {
        int[][] board = { { 0, 0 }, { 0, 0 } };
        assertEquals(false, recursion.nQueen(board));

    }

    @Test
    public void negativenQueenTest() {
        int[][] board = new int[0][0];
        AssertionError e2 = assertThrows(AssertionError.class, () -> {
            recursion.nQueen(board);
        });
        assertEquals("Board size should be greater than 0", e2.getMessage());
    }
}
