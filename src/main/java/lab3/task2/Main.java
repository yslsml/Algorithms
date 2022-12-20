package lab3.task2;

import lab3.task2.algorithm.Solution;
import lab3.task2.util.MatrixUtils;

public class Main {
//    O(n^3)
//    O(n^2) * n = O(n^3)

    public static void main(String[] args) {

        double inf = Double.POSITIVE_INFINITY;
        Double[][] matrix2 = new Double[][] {
                {inf, 9.0, 4.0, 7.0, 1.0},
                {4.0, inf, 8.0, 2.0, 1.0},
                {2.0, 3.0, inf, 5.0, 4.0},
                {3.0, 2.0, inf, inf, 6.0},
                {6.0, 6.0, 2.0, 10.0, inf}
        };

        Double[][] matrix = MatrixUtils.initRandomAdjacencyMatrix(5);

        System.out.println("\nInitial random matrix: \n");
        MatrixUtils.printMatrix(matrix);

        Solution solutionFloyd = new Solution(Solution.SolutionType.FLOYD);
        System.out.println("\nThe vertex with the minimum time to the furthest vertex is " + solutionFloyd.solve(matrix) + " (by Floyd)");

        Solution solutionDijkstra = new Solution(Solution.SolutionType.DIJKSTRA);
        System.out.println("\nThe vertex with the minimum time to the furthest vertex is " + solutionDijkstra.solve(matrix) + " (by Dijkstra)");
    }

}
