package lab2.task1;

import lab2.util.ArrayHandler;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        final int SIZE = 100;
        final int MIN_VALUE = 0;
        final int MAX_VALUE = 100;

        int[] array = ArrayHandler.createArray(SIZE, MIN_VALUE, MAX_VALUE);
        System.out.println("Given array: \n" + Arrays.toString(array));
        Arrays.sort(array);
        System.out.println("Sorted array: \n" + Arrays.toString(array));

        int key = ArrayHandler.getRandomKey(MIN_VALUE, MAX_VALUE);
        System.out.println("\nKey value is " + key);

        int binarySearchResult = Search.binarySearch(array, key);
        System.out.println("Index of key element in binary search is " + binarySearchResult);

        int interpolationSearchResult = Search.interpolationSearch(array, key);
        System.out.println("Index of key element in interpolation search is " + interpolationSearchResult);
    }
}
