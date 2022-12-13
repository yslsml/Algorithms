package lab2.task1;

import java.util.Arrays;

public class Search {

    public static int binarySearch(int[] array, int key) {
        Arrays.sort(array);
        int countOperations = 0;
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        int index = -1;

        while (leftIndex <= rightIndex) {
            int middleIndex = (leftIndex + rightIndex) / 2;
            if (array[middleIndex] == key) {
                countOperations++;
                index = middleIndex;
                break;
            } else if (array[middleIndex] < key) {
                countOperations++;
                leftIndex = middleIndex + 1;
            } else {
                // countOperations++;
                rightIndex = middleIndex - 1;
            }
        }

        System.out.println("\nNumber of comparison operations in binary search is " + countOperations);
        return index;
    }

    public static int interpolationSearch(int[] array, int key) {
        Arrays.sort(array);
        int countOperations = 0;
        int leftIndex = 0;
        int rightIndex = array.length - 1;
        int index = -1;

        while (leftIndex <= rightIndex && array[leftIndex] <= key && array[rightIndex] >= key) {
            int middleIndex = leftIndex + (rightIndex - leftIndex) / (array[rightIndex] - array[leftIndex]) * (key - array[leftIndex]);
            if (array[middleIndex] == key) {
                countOperations++;
                index = middleIndex;
                break;
            } else if (array[middleIndex] < key) {
                countOperations++;
                leftIndex = middleIndex + 1;
            } else {
                // countOperations++;
                rightIndex = middleIndex - 1;
            }
        }

        System.out.println("\nNumber of comparison operations in interpolation search is " + countOperations);
        return index;
    }

}
