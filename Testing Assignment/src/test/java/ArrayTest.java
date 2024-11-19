package test.java;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import Main.java.ArrayOperations;

public class ArrayTest {
    ArrayOperations arrayOperations = new ArrayOperations();

    // @ParameterizedTest
    // @MethodSource("testInput")
    // public void testArrayOperation(int input[], int output) {
    // assertEquals(a.maxMirror(input), output);
    // }

    @Test
    public void countClumbsTest() {

        int[] arr = { 1, 2, 2, 1, 4 };
        assertEquals(1, arrayOperations.countClumps(arr));
    }

    @Test
    public void splitArrayTest() {
        int[] arr = { 1, 2, 3 };
        assertEquals(2, arrayOperations.splitArray(arr));
    }

    @Test
    public void fixXYTest() {

        int[] b = { 5, 6, 2, 4, 1, 9, 2, 7, 1 };
        int[] ans = { 5, 6, 2, 1, 4, 9, 2, 1, 7 };
        assertArrayEquals(ans, arrayOperations.fixXY(2, 1, b));

    }

    /** parameterized test cases */

    private static Stream<Arguments> mirrorTestInput() {
        return Stream.of(
                Arguments.of(new int[] { 7, 1, 4, 9, 7, 4, 1 }, 2),
                Arguments.of(new int[] { 1, 2, 1, 4 }, 3),
                Arguments.of(new int[] { 1, 2, 3, 8, 9, 3, 2, 1 }, 3),
                Arguments.of(new int[] { 1, 4, 5, 3, 5, 4, 1 }, 7)

        );

    }

    @ParameterizedTest
    @MethodSource("mirrorTestInput")
    public void maxMirrorTest(int[] input, int output) {

        assertEquals(output, arrayOperations.maxMirror(input));

    }

    private static Stream<Arguments> clumbTestInput() {
        return Stream.of(
                Arguments.of(new int[] { 1, 2, 2, 3, 4, 4 }, 2),
                Arguments.of(new int[] { 1, 1, 2, 1, 1 }, 2),
                Arguments.of(new int[] { 1, 1, 1, 1, 1 }, 1));

    }

    @ParameterizedTest
    @MethodSource("clumbTestInput")
    public void countClumpsTest(int[] input, int output) {

        assertEquals(output, arrayOperations.countClumps(input));

    }

    private static Stream<Arguments> fixXYTestInput() {
        return Stream.of(
                Arguments.of(new int[] { 5, 4, 9, 4, 9, 5 }, new int[] { 9, 4, 5, 4, 5, 9 }),
                Arguments.of(new int[] { 1, 4, 1, 5 }, new int[] { 1, 4, 5, 1 }),
                Arguments.of(new int[] { 1, 4, 1, 5, 5, 4, 1 }, new int[] { 1, 4, 5, 1, 1, 4, 5 })

        );
    }

    @ParameterizedTest
    @MethodSource("fixXYTestInput")
    public void fixXYTest(int[] input, int[] output) {
        int X = 4, Y = 5;

        assertArrayEquals(output, arrayOperations.fixXY(X, Y, input));

    }

    private static Stream<Arguments> splitArrayTestInput() {
        return Stream.of(
                Arguments.of(new int[] { 1, 1, 1, 2, 1 }, 3),
                Arguments.of(new int[] { 2, 1, 1, 2, 1 }, -1),
                Arguments.of(new int[] { 10, 10 }, 1)

        );
    }

    @ParameterizedTest
    @MethodSource("splitArrayTestInput")
    public void splitArrayTest(int[] input, int output) {
        assertEquals(output, arrayOperations.splitArray(input));

    }

    /** negetive test Cases */
    @Test
    void negetiveTestCase() {
        int arr[] = new int[0];
        AssertionError e1 = assertThrows(AssertionError.class, () -> {
            arrayOperations.maxMirror(arr);
        });
        assertEquals("Input array can't be empty", e1.getMessage());

        AssertionError e2 = assertThrows(AssertionError.class, () -> {
            arrayOperations.countClumps(arr);
        });
        assertEquals("Input array can't be empty", e2.getMessage());
    }

    @Test
    void negetiveTestFixXY() {
        int X = 1;
        int Y = 2;
        int[] array1 = {};
        int[] array2 = { 2, 1, 4, 2, 1 };
        int[] array3 = { 2, 1, 1, 5, 6, 3 };
        int[] array4 = { 1, 3, 1, 2, 4 };

        AssertionError e1 = assertThrows(AssertionError.class, () -> {
            arrayOperations.fixXY(X, Y, array1);
        });
        assertEquals("Input array can't be empty", e1.getMessage());

        AssertionError e2 = assertThrows(AssertionError.class, () -> {
            arrayOperations.fixXY(X, Y, array2);
        });
        assertEquals("Array can't be fixed because last element is X itself", e2.getMessage());

        AssertionError e3 = assertThrows(AssertionError.class, () -> {
            arrayOperations.fixXY(X, Y, array3);
        });
        assertEquals("Consicutive X", e3.getMessage());

        AssertionError e4 = assertThrows(AssertionError.class, () -> {
            arrayOperations.fixXY(X, Y, array4);
        });
        assertEquals("Array is not fixable", e4.getMessage());

    }

}
