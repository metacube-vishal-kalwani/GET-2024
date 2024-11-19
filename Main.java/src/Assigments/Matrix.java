/**
 * Assignment 7 Using Invariants in Design and Implementation of ADTs
 * @Auther vishal kalwani
 */

package Assigments;

import java.util.HashMap;
import java.util.Map;

/**
 * Class Matrix is the implementation of an abstract immutable matrix which will
 * store a sparse matrix and it's methods
 * Methods -
 * 1 add values() : takes row ,coloum, value and puts in matrix
 * 2 transpose() : returns transpose of a matrix
 * 3 isSymetric() : returns true if matrix is symmetric
 * 4 addition() : returns addition of two matrices
 * 5 multiplication(): returns multiplication of two matrices
 */
public class Matrix {

    // Stores matrix in form of map for optimisation purpose
    public Map<String, Integer> matrix;
    public int maxRows;
    public int maxCols;

    public Matrix(int maxRows, int maxCols) {
        matrix = new HashMap<>();
        this.maxRows = maxRows;
        this.maxCols = maxCols;
    }

    /**
     * Takes row , column and values and stores in matrix
     * 
     * @param currentRow
     * @param currentCol
     * @param value
     */
    public void addValues(int currentRow, int currentCol, int value) {
        if (value == 0) {
            return;
        }
        String key = currentRow + "," + currentCol;
        matrix.put(key, value);
    }

    /**
     * reverse the given string
     * 
     * @param inputString
     * @return
     */
    private String reverseString(String inputString) {

        StringBuffer reverseString = new StringBuffer();
        String[] array = inputString.split(",");

        for (int itorator = array.length - 1; itorator >= 0; itorator--) {
            reverseString.append(array[itorator]);
            if (itorator != 0) {

                reverseString.append(",");
            }

        }

        return reverseString.toString();

    }

    /**
     * int transpose , all the row element becomes column and all the column element
     * becomes row
     * 
     * @return returns the transpose of matrix
     */
    public Matrix transpose() {
        Matrix transposeMatrix = new Matrix(maxCols, maxRows);

        for (Map.Entry<String, Integer> entry : matrix.entrySet()) {
            String key = entry.getKey();
            String newKey = reverseString(key);
            transposeMatrix.matrix.put(newKey, entry.getValue());
        }
        return transposeMatrix;
    }

    /**
     * Prints the matrix in grid format
     */
    public void printMatrix() {

        for (int indexI = 0; indexI < this.maxRows; indexI++) {
            for (int indexJ = 0; indexJ < this.maxCols; indexJ++) {
                String key = indexI + "," + indexJ;
                System.out.print(this.matrix.getOrDefault(key, 0) + " ");
            }
            System.out.println();
        }

    }

    /**
     * A metrix is symmetric if the orginal matrix is same as it's transpose matrix
     * 
     * @return returns true if matrix is symmetric
     */
    public boolean isSymetric() {

        Matrix transposeMatrix = this.transpose();
        for (Map.Entry<String, Integer> entry : this.matrix.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            if (!transposeMatrix.matrix.containsKey(key) || transposeMatrix.matrix.get(key) != value) {
                return false;
            }
        }
        return true;
    }

    /**
     * performs addition of two matrices only if there order is same
     * 
     * @param inputMatrix takes another matrix from user
     * @return return the addition of to matrices
     */
    public Matrix addition(Matrix inputMatrix) {
        if (inputMatrix.maxRows != this.maxRows || inputMatrix.maxCols != this.maxCols) {
            throw new ArithmeticException("Order is not same, addition is not possible!!");
        }
        Matrix newMatrix = new Matrix(maxRows, maxCols);

        for (int currentRow = 0; currentRow < maxRows; currentRow++) {
            for (int currentCol = 0; currentCol < maxCols; currentCol++) {
                String key = currentRow + "," + currentCol;

                // firstVal takes value from first matrix
                int firstVal = 0;
                // secondVal takes value from second matrix
                int secondVal = 0;
                if (matrix.containsKey(key)) {
                    firstVal = matrix.get(key);
                }
                if (inputMatrix.matrix.containsKey(key)) {
                    secondVal = inputMatrix.matrix.get(key);
                }
                int sum = firstVal + secondVal;
                if (sum != 0) {
                    newMatrix.matrix.put(key, sum);
                }
            }
        }
        return newMatrix;
    }

    /**
     * performs multiplication of two matrices only if there column in first matrix
     * is same as rows in second matrix
     * 
     * @param inputMatrix takes another matrix from user
     * @return return the multiplication of to matrices
     */
    public Matrix multiplication(Matrix inputMatrix) {
        if (this.maxCols != inputMatrix.maxRows) {
            throw new ArithmeticException("Multiplication not possible (order is not matching) !!");
        }
        Matrix newMatrix = new Matrix(this.maxRows, inputMatrix.maxCols);

        int rowIndex = 0;
        /* Iterating over first matrix's rows */
        while (rowIndex < this.maxRows) {

            int colIndex = 0;
            /* Iterating over second matrix's columns */
            while (colIndex < inputMatrix.maxCols) {
                int elementIndex = 0;
                int totalValue = 0;

                /** accessing each element in row and column to multiply */
                while (elementIndex < this.maxCols) {

                    String firstMatrixIndex = rowIndex + "," + elementIndex;
                    String secondMatixIndex = elementIndex + "," + colIndex;
                    int multipliedvalue = this.matrix.getOrDefault(firstMatrixIndex, 0) *
                            inputMatrix.matrix.getOrDefault(secondMatixIndex, 0);
                    totalValue += multipliedvalue;
                    elementIndex++;
                }
                String newMatrixKey = rowIndex + "," + colIndex;
                if (totalValue != 0) {
                    newMatrix.matrix.put(newMatrixKey, totalValue);
                }

                colIndex++;
            }
            rowIndex++;
        }
        return newMatrix;
    }

}
