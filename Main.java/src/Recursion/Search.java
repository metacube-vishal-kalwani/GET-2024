/**
 * Goal - software construction and fundamental
 * Assignment 5 : Recursion
 * @author Vishal kalwani
 * @version 1.0
 * 
 */
package Recursion;

/**
 * Class Search implements to two methods to find an element in an array
 * linearSearch() : performs linearSearch to find the element
 * binarySearch() : performs binarySearch to find the element
 */
public class Search {
    /**
     * This method performs binary search on an array
     * 
     * @param array         takes input array
     * @param elementToFind takes element to be searched in array
     * @param currentindex  takes starting index of array i.e 0
     * @return returns the index of element if it's present and returns -1 if not
     */
    public int linearSearch(int[] array, int currentindex, int elementToFind) {
        if (currentindex == array.length)
            return -1;

        if (array[currentindex] == elementToFind) {
            return currentindex;
        }
        return linearSearch(array, currentindex + 1, elementToFind);

    }

    /**
     * 
     * @param array         sorted array in which function finds element's position
     * @param startIndex    starting index of array
     * @param endIndex      ending index of array
     * @param elementToFind element to be find in array
     * @return returns the index of the search element
     */
    private int binarySearchHelper(int[] array, int startIndex, int endIndex, int elementToFind) {
        if (startIndex > endIndex) {
            return -1;
        }

        int mid = (startIndex + endIndex) / 2;
        if (array[mid] == elementToFind)
            return mid;
        else if (array[mid] < elementToFind) {
            return binarySearchHelper(array, mid + 1, endIndex, elementToFind);
        } else {
            return binarySearchHelper(array, startIndex, mid - 1, elementToFind);

        }
    }

    /**
     * This method performs binary search on an array
     * 
     * @param array         takes input array
     * @param elementToFind takes element to be searched in array
     * @return returns the index of element if it's present and returns -1 if not
     */

    public int binarySearch(int[] array, int elementToFind) {
        return binarySearchHelper(array, 0, array.length - 1, elementToFind);
    }
}
