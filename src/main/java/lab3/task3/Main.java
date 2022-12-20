package lab3.task3;

import lab3.task2.util.MatrixUtils;
import lab3.task3.algorithm.KruskalAlgorithm;
import lab3.task3.algorithm.PrimAlgorithm;

public class Main {
//    O(n^2)
//    O(m*ln(n))

    public static void main(String[] args) {
        double inf = Double.POSITIVE_INFINITY;
        Double[][] matrix = new Double[][] {
                {inf, 3.0, inf, inf, inf, 5.0, 2.0},
                {3.0, inf, 5.0, 8.0, inf, inf, inf},
                {inf, 5.0, inf, 1.0, inf, inf, 6.0},
                {inf, 8.0, 1.0, inf, 4.0, inf, inf},
                {inf, inf, inf, 4.0, inf, 1.0, 4.0},
                {5.0, inf, inf, inf, 1.0, inf, 2.0},
                {2.0, inf, 6.0, inf, 4.0, 2.0, inf}
        };

        System.out.println("\nInitial matrix: \n");
        MatrixUtils.printMatrix(matrix);

        System.out.println("\n====== PRIM ALGORITHM ======\n");
        PrimAlgorithm primAlgorithm = new PrimAlgorithm();
        var pGraph = primAlgorithm.buildWeb(matrix);
        System.out.println("\nObtained graph: \n\n" + pGraph);
        System.out.println("\nThe weight of this graph is " + pGraph.getWeight());

        System.out.println("\n====== KRUSKAL ALGORITHM ======\n");
        KruskalAlgorithm kruskalAlgorithm = new KruskalAlgorithm();
        var kGraph = kruskalAlgorithm.buildWeb(matrix);
        System.out.println("\nObtained graph: \n\n" + kGraph);
        System.out.println("\nThe weight of this graph is " + kGraph.getWeight());

    }
}










//        Double[][] matrix2 = new Double[][] {
//                {inf, 2.0, inf, inf, inf, inf, inf, inf, 3.0, 3.0, inf, 1.0},
//                {2.0, inf, 4.0, inf, inf, inf, inf, inf, inf, inf, 1.0, 4.0},
//                {inf, 4.0, inf, 2.0, inf, inf, inf, inf, inf, inf, 2.0, inf},
//                {inf, inf, 2.0, inf, 5.0, inf, inf, inf, inf, inf, 3.0, inf},
//                {inf, inf, inf, 5.0, inf, 3.0, inf, 1.0, inf, inf, inf, inf},
//                {inf, inf, inf, inf, 3.0, inf, 4.0, 2.0, inf, inf, inf, inf},
//                {inf, inf, inf, inf, inf, 4.0, inf, 1.0, inf, inf, inf, inf},
//                {inf, inf, inf, inf, 1.0, 2.0, 1.0, inf, inf, 4.0, inf, inf},
//                {3.0, inf, inf, inf, inf, inf, inf, inf, inf, 2.0, inf, inf},
//                {3.0, inf, inf, inf, inf, inf, inf, 4.0, 2.0, inf, 1.0, inf},
//                {inf, 1.0, 2.0, 3.0, inf, inf, inf, inf, inf, 1.0, inf, 3.0},
//                {1.0, 4.0, inf, inf, inf, inf, inf, inf, inf, inf, 3.0, inf}
//        };