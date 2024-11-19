package Test.java;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import Assigments.Matrix;

import static org.junit.Assert.assertEquals;

import java.util.stream.Stream;

public class MatrixTest {

    Matrix createMatrix(int[][] array) {
        Matrix testMatrix = new Matrix(array.length, array[0].length);

        for (int indexI = 0; indexI < array.length; indexI++) {
            for (int indexJ = 0; indexJ < array[0].length; indexJ++) {
                testMatrix.addValues(indexI, indexJ, array[indexI][indexJ]);
            }
        }
        return testMatrix;
    }

    public static Stream<Arguments> transposeMatrixTestInput() {
        return Stream.of(
                Arguments.of(new int[][] { { 1, 0, 1 }, { 1, 1, 0 } }, new int[][] { { 1, 0, 1 }, { 0, 1, 1 } }),
                Arguments.of(new int[][] { { 1, 0, 1 }, { 1, 1, 0 } }, new int[][] { { 1, 1 }, { 0, 1 }, { 1, 0 } })

        );

    }

    @ParameterizedTest
    @MethodSource("transposeMatrixTestInput")
    public void testMatrixTranspose(int[][] input, int[][] output) {

        Matrix inputMatrix = createMatrix(input);
        Matrix expectedMatrix = createMatrix(output);

        assertEquals(expectedMatrix.matrix, inputMatrix.transpose().matrix);

    }

    public static Stream<Arguments> multiplicationMatrixTestInput() {
        return Stream.of(
                Arguments.of(new int[][] { { 1, 2 }, { 3, 4 } }, new int[][] { { 2 }, { 3 } },
                        new int[][] { { 8 }, { 18 } }),
                Arguments.of(new int[][] { { 3, 0, 7 }, { 3, 0, 0 }, { 1, 2, 3 } }, new int[][] { { 2 }, { 3 }, { 4 } },
                        new int[][] { { 34 }, { 6 }, { 20 } }));

    }

    @ParameterizedTest
    @MethodSource("multiplicationMatrixTestInput")
    public void multiplicationMatrixTest(int[][] input1, int[][] input2, int[][] output) {

        Matrix inputMatrix1 = createMatrix(input1);
        Matrix inputMatrix2 = createMatrix(input2);
        Matrix expectedMatrix = createMatrix(output);
        assertEquals(expectedMatrix.matrix, inputMatrix1.multiplication(inputMatrix2).matrix);

    }

    public static Stream<Arguments> additionMatrixTestInput() {
        return Stream.of(
                Arguments.of(new int[][] { { 1, 2 }, { 3, 4 } }, new int[][] { { 2, 0 }, { 0, 9 } },
                        new int[][] { { 3, 2 }, { 3, 13 } }),
                Arguments.of(new int[][] { { 1, 2, 98 }, { 3, 4, 0 } }, new int[][] { { 2, 20, 0 }, { 0, 0, 0 } },
                        new int[][] { { 3, 22, 98 }, { 3, 4, 0 } }));

    }

    @ParameterizedTest
    @MethodSource("additionMatrixTestInput")
    public void additionMatrixTest(int[][] input1, int[][] input2, int[][] output) {

        Matrix inputMatrix1 = createMatrix(input1);
        Matrix inputMatrix2 = createMatrix(input2);
        Matrix expectedMatrix = createMatrix(output);
        assertEquals(expectedMatrix.matrix, inputMatrix1.addition(inputMatrix2).matrix);
    }

    public static Stream<Arguments> symmetricMatrixTestInput() {
        return Stream.of(
                Arguments.of(new int[][] { { 1, 0, 1 }, { 1, 1, 0 } }, false),
                Arguments.of(new int[][] { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } }, true)

        );

    }

    @ParameterizedTest
    @MethodSource("symmetricMatrixTestInput")
    public void symmetricMatrixTest(int[][] input, boolean output) {

        Matrix inputMatrix = createMatrix(input);

        assertEquals(output, inputMatrix.isSymetric());

    }
}
