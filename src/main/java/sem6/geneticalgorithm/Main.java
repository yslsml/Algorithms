package sem6.geneticalgorithm;


public class Main {
    public static void main(String[] args) {
        int[][] firstEquationPowers = {{2, 2, 2, 2, 2}, {0, 0, 0, 0, 1}, {2, 1, 0, 1, 1}, {1, 1, 0, 1, 2}, {0, 0, 1, 1, 0}};
        int[][] secondEquationPowers = {{2, 2, 2, 2, 0}, {0, 0, 0, 0, 1}, {0, 0, 1, 1, 0}, {2, 1, 1, 2, 1}, {0, 0, 2, 0, 2}};
        int[][] thirdEquationPowers = {{2, 0, 1, 0, 1}, {1, 2, 2, 1, 2}, {1, 0, 1, 0, 2}, {1, 1, 2, 2, 0}, {0, 0, 0, 0, 1}};

        new EquationHandler(firstEquationPowers, -50).start();
        new EquationHandler(secondEquationPowers, 13).start();
        new EquationHandler(thirdEquationPowers, -50).start();
    }
}

//       1. Best solution {x1=-50, x2=-1, x3=-1, x4=-1, x5=-1}
//        Iteration: 3050 solution: 0.0
//
//
//       2. Best solution {x1=-2, x2=-1, x3=-2, x4=-1, x5=-1}
//        Iteration: 14900 solution: 0.0
//
//       3. Best solution {x1=-1, x2=-1, x3=-1, x4=-1, x5=-1} - нет решений
//        Iteration: 1000001 solution error: 53.0