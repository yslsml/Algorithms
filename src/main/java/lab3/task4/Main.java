package lab3.task4;

import lab3.task2.util.MatrixUtils;
import lab3.task4.algorithm.HungarianAlgorithm;

public class Main {

    public static void main(String[] args) {
//        O(n^3)
//        float[][] matrix2 = {{7, 3, 6, 9, 5}, {7, 5, 7, 5, 6}, {7, 6, 8, 8, 9}, {3, 1, 6, 5, 7}, {2, 4, 9, 9, 5}};
//        float[][] matrix3 = {{8,6,0,8},{4,3,4,0},{7,8,2,2},{2,9,9,3}};

        System.out.println("\nIt is necessary to allocate workers to tasks so that there are minimal work costs.");
        float[][] costs = {{32, 28, 4, 26, 4}, {17, 19, 4, 17, 4}, {4, 4, 5, 4, 4}, {17, 14, 4, 14, 4}, {21, 16, 4, 13, 4}};

        System.out.println("\nInitial matrix of costs: ");
        MatrixUtils.printMatrix(costs);

        int[][] optimalAssignment = new HungarianAlgorithm(costs).findOptimalAssignment();
        System.out.println("Result of hungarian algorithm (sum) : " + HungarianAlgorithm.getOptimalSum(costs, optimalAssignment));
        System.out.println("\nIndexes of chosen elements: ");
        MatrixUtils.printMatrix(optimalAssignment);
    }
}
