/**
 * Assignment 5 - abstract and concrete data types
 * @Auther vishal kalwani
 * 
 */

package Assigments;

/**
 * Class IntSet is an immutable abstract data type which always stores positive
 * values range between (1 to 1000)
 * Methods :
 * 1 isMember() : checks whether the value is present in set or not
 * 2 subSet() : checks input subset is a subset of original set
 * 3 complement() : returns the complement set of the input set
 * 4 addToSet() : adds a value to set
 * 5 union() : returns Union of sets
 */

public class IntSet {

    private boolean[] array;
    private int setSize;
    private static final int LOWER_RANGE = 1;
    private static final int UPPER_RANGE = 1000;

    /* Constructor to initialize Set */
    IntSet() {
        array = new boolean[1001];
        setSize = 0;
    }
    // 1 2 3
    // arr[1] true
    // arr[2] true

    /**
     * validates the user input
     * 
     * @param userInput integer input entered by user
     * @return returns true if input falls in the Given range (1 to 1000)
     */
    private boolean validateSetInput(int userInput) {
        if (userInput >= LOWER_RANGE && userInput <= UPPER_RANGE) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks whether input is present in set or not
     * 
     * @param inputValue takes input from the user
     * @return returns true if it's present in set otherwise returns true
     */
    boolean isMember(int inputValue) {
        if (validateSetInput(inputValue) == false) {
            throw new AssertionError("value Should be in range of (1,1000) !!!");
        }
        return (array[inputValue] ? true : false);

    }

    /**
     * Prints all the values of Set
     */
    void printAll() {
        for (int i = LOWER_RANGE; i <= UPPER_RANGE; i++) {
            if (this.isMember(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    /* Returns the size of intSet */
    int size() {
        return setSize;
    }

    /**
     * Function to add a input value to Set
     * 
     * @param inputValue integer input from the user
     */
    public void addToSet(int inputValue) {
        if (validateSetInput(inputValue) == false) {
            throw new AssertionError("Value should be in range of (1,1000)!!!");
        }

        if (this.array[inputValue] == false) {
            setSize++;
        }
        this.array[inputValue] = true;
    }

    /**
     * This function performs complement of a set
     * 
     * @return returns the Complement of Set
     */
    public IntSet getComplement() {
        IntSet complementSet = new IntSet();

        for (int i = LOWER_RANGE; i <= UPPER_RANGE; i++) {
            if (!isMember(i)) {
                complementSet.addToSet(i);
            }
        }
        return complementSet;
    }

    /**
     * This function checks whether a given set is subset of original set
     * 
     * @param inputSubSet takes the set from user
     * @return returns true if input subset is subset of original set
     */
    public boolean isSubSet(IntSet inputSubSet) {

        for (int i = LOWER_RANGE; i <= UPPER_RANGE; i++) {
            if (inputSubSet.isMember(i) && (!array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * This function finds union of two sets
     * 
     * @param inputIntSet Takes input from the user
     * @return returns union of inputSet and original set
     */
    public IntSet union(IntSet inputIntSet) {
        IntSet unionSet = new IntSet();

        for (int i = LOWER_RANGE; i <= UPPER_RANGE; i++) {
            if (inputIntSet.isMember(i) || this.array[i] == true) {
                unionSet.addToSet(i);
            }
        }

        return unionSet;

    }

}
