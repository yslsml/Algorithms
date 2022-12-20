package lab3.task2.util;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringJoiner;

public class MatrixUtils {
    public static <T> void printMatrix2(T[][] matrix) {
        StringJoiner joiner = new StringJoiner("\n", "", "");
        for (T[] ts : matrix) {
            joiner.add(Arrays.toString(ts));
        }
        System.out.println(joiner);
    }

    public static <T> boolean isSquare(T[][] matrix) {
        int size = matrix.length;
        int i = 0;
        while (i < matrix.length) {
            if (matrix[i].length != size) {
                return false;
            }
            i++;
        }
        return true;
    }

    public static <T> T[][] copyMatrix(T[][] matrix) {
        T[][] copy = (T[][]) Array.newInstance(matrix.getClass().getComponentType(), matrix.length);
        for (int i = 0; i < copy.length; i++) {
            copy[i] = Arrays.copyOf(matrix[i], matrix[i].length);
        }
        return copy;
    }

    public static <T> boolean containsNull(T[][] matrix) {
        if (matrix == null) {
            return true;
        }
        int i = 0;
        while (i < matrix.length) {
            if (matrix[i] == null) {
                return true;
            }
            int j = 0;
            while (j < matrix[i].length) {
                if (matrix[i][j] == null) {
                    return true;
                }
                j++;
            }
            i++;
        }
        return false;
    }

    public static Double[][] initRandomAdjacencyMatrix(int size){
        Double[][] matrix = new Double[size][size];
        for(int i=0; i<size;i++){
            for(int j = 0; j<size; j++){
                if(i == j){
                    matrix[i][j]= Double.POSITIVE_INFINITY;
                }else{
                    matrix[i][j]=(Double) (Math.random()*20)+1;
                }
            }
        }
        return matrix;
    }


    public static void printMatrix(Double[][] matrix){
        for(int i=0; i< matrix.length;i++){
            System.out.print("[ ");
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == Double.POSITIVE_INFINITY){
                    System.out.print("INF");
                }else{
                    System.out.printf("%.2f", matrix[i][j]);
                }
                if(j!=matrix.length-1) {
                    System.out.print(". ");
                }
            }
            System.out.println(" ]");
        }
        System.out.println();
    }

    public static void printMatrix(int[][] matrix){
        for(int i=0; i< matrix.length;i++){
            System.out.print("[ ");
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == Double.POSITIVE_INFINITY){
                    System.out.print("INF");
                }else{
                    System.out.printf("%d", matrix[i][j]);
                }
                if(j!=matrix.length-1) {
                    System.out.print(". ");
                }
            }
            System.out.println(" ]");
        }
        System.out.println();
    }

    public static void printMatrix(float[][] matrix){
        for(int i=0; i< matrix.length;i++){
            System.out.print("[ ");
            for(int j = 0; j < matrix[i].length; j++){
                if(matrix[i][j] == Double.POSITIVE_INFINITY){
                    System.out.print("INF");
                }else{
                    System.out.printf("%.0f", matrix[i][j]);
                }
                if(j!=matrix.length-1) {
                    System.out.print(". ");
                }
            }
            System.out.println(" ]");
        }
        System.out.println();
    }


    private MatrixUtils() {}
}

