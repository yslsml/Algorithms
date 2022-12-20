package lab3.task5;

import lab3.task5.algorithm.GaleShapleyAlgorithm;

public class Main {
    //        O(n^2)

    public static void main(String[] args) {

        int[][] workersPriorities = {{1, 0, 2, 3}, {2, 3, 1, 0}, {2, 1, 3, 0}, {0, 3, 2, 1}};
        int[][] tasksPriorities = {{2, 3, 1, 0}, {2, 1, 0, 3}, {0, 1, 2, 3}, {3, 2, 1, 0}};

        new GaleShapleyAlgorithm().moreEffectiveEmployeeForTask(workersPriorities, tasksPriorities);
    }
}
