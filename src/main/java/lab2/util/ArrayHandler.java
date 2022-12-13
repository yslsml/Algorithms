package lab2.util;

public class ArrayHandler {

    public static int[] createArray(int size, int start, int end) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = ((int) (Math.random() * (end - start + 1)));
        }
        return array;
    }

    public static int getRandomKey(int start, int end) {
        return ((int) (Math.random() * (end - start + 1)));
    }

}
