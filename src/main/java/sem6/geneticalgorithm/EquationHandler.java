package sem6.geneticalgorithm;

import java.util.*;
import java.util.stream.Collectors;

/**
 * вариант 11
 *
 * Начальная популяция: случайная в (-100, 100)
 * Селекция: турнирная
 * Скрещивание: одноточечное (x3) + многоточечное (x2, x4)
 * Мутация: каждый бит всех потомков мутирует с некоторой вероятностью p
 * Замещение: произвести больше потомков, и оставить популяцию такого же размера,
 * как старая, но состоящую исключительно из более пригодных потомков
 */


class EquationHandler {
    private final int minValue = -100;
    private final int maxValue = 100;
    private final int populationCount = 10;
    private final double mutationChanceForOffspring = 0.5;
    private int[][] powers;
    private int result;

    public EquationHandler(int[][] powers, int result) {
        this.powers = powers;
        this.result = result;
    }

    private double solutionError(Equation equation) {
        return Math.abs(
                Math.pow(equation.x1, powers[0][0])*Math.pow(equation.x2, powers[0][1])*Math.pow(equation.x3, powers[0][2])*Math.pow(equation.x4, powers[0][3])*Math.pow(equation.x5, powers[0][4]) + Math.pow(equation.x1, powers[1][0])*Math.pow(equation.x2, powers[1][1])*Math.pow(equation.x3, powers[1][2])*Math.pow(equation.x4, powers[1][3])*Math.pow(equation.x5, powers[1][4]) + Math.pow(equation.x1, powers[2][0])*Math.pow(equation.x2, powers[2][1])*Math.pow(equation.x3, powers[2][2])*Math.pow(equation.x4, powers[2][3])*Math.pow(equation.x5, powers[2][4]) + Math.pow(equation.x1, powers[3][0])*Math.pow(equation.x2, powers[3][1])*Math.pow(equation.x3, powers[3][2])*Math.pow(equation.x4, powers[3][3])*Math.pow(equation.x5, powers[3][4]) + Math.pow(equation.x1, powers[4][0])*Math.pow(equation.x2, powers[4][1])*Math.pow(equation.x3, powers[4][2])*Math.pow(equation.x4, powers[4][3])*Math.pow(equation.x5, powers[4][4]) - result
        );
    }

    private double fitnessFunction(Equation equation) {
        return 1/Math.abs(0.00000001 + ((double) solutionError(equation)));
    }

    public void start() {
        List<Equation> population = new Equation().generatePopulation(populationCount, minValue, maxValue);
        double bestSolutionError = fitnessFunction(population.get(0));
        Equation bestSolution = population.get(0);
        int iteration = 0;
        while(bestSolutionError < 1) {
            List<Equation> newPopulation = selection(new ArrayList<>(population));
            List<Equation> nextPopulation = substitution(population, newPopulation);
            double newBestSolutionError = fitnessFunction(nextPopulation.get(0));
            Equation newBestSolution = nextPopulation.get(0);
            if (newBestSolutionError > bestSolutionError) {
                bestSolution = newBestSolution;
                bestSolutionError = newBestSolutionError;
            }
            population = nextPopulation;
            iteration++;
            if (iteration > 1000000) {
                break;
            }
        }
        System.out.println("Best solution" + bestSolution.toString() + "\nIteration: " + iteration + " solution error: " + solutionError(bestSolution)+"\n\n");

    }

    public ArrayList<Equation> substitution(List<Equation> oldPopulation, List<Equation> newPopulation) {
        List<Equation> mergedPopulation = new ArrayList<>();
        mergedPopulation.addAll(oldPopulation);
        mergedPopulation.addAll(newPopulation);

        mergedPopulation.sort(Comparator.comparingDouble(this::fitnessFunction).reversed());

        return new ArrayList<>(mergedPopulation.subList(0, populationCount));
    }

    public ArrayList<Equation> selection(ArrayList<Equation> old) {
        List<Equation> children = new ArrayList<>();
        List<Double> errorsOfEquations = new ArrayList<>();
        for (Equation i : old) {
            double tmp = fitnessFunction(i);
            errorsOfEquations.add(tmp);
        }

        Random rand = new Random(System.currentTimeMillis());
        for (int i = 0; i < populationCount; i++) {
            List<Equation> tournament = new ArrayList<>();
            int tournamentSize = 3;
            for (int j = 0; j < tournamentSize; j++) {
                int randomIndex = rand.nextInt(old.size());
                tournament.add(old.get(randomIndex));
            }
            Equation winner = tournament.stream()
                    .max(Comparator.comparingDouble(this::fitnessFunction))
                    .orElseThrow(NoSuchElementException::new);
            children.add(winner);
        }

        children = crossover((ArrayList<Equation>) children);
        children = mutation((ArrayList<Equation>) children);

        return (ArrayList<Equation>) children;
    }


    public List<Equation> crossover(ArrayList<Equation> old) {
        List<Equation> newChildren = new ArrayList<>();
        double crossoverChoose = 0.5;
        Random rand  = new Random(System.currentTimeMillis());
        for(int i = 0; i < old.size() - 1; i+=2){
            Equation parent1 = old.get(i);
            Equation parent2 = old.get(i+1);
            double tmp = rand.nextDouble();
            if (tmp > crossoverChoose) {
                newChildren.add(new Equation(parent1.x1, parent1.x2, parent1.x3, parent2.x4, parent2.x5));
                newChildren.add(new Equation(parent2.x1, parent2.x2, parent2.x3, parent1.x4, parent1.x5));
            } else {
                newChildren.add(new Equation(parent1.x1, parent1.x2, parent2.x3, parent1.x4, parent1.x5));
                newChildren.add(new Equation(parent2.x1, parent2.x2, parent1.x3, parent1.x4, parent1.x5));
            }
        }
        return newChildren;
    }

    private List<Equation> mutation(ArrayList<Equation> old) {
        Random rand = new Random();
        return old.stream()
                .map(i -> {
                    int x1 = isTrue(mutationChanceForOffspring) ? i.generateGenome(minValue, maxValue) : i.x1;
                    int x2 = isTrue(mutationChanceForOffspring) ? i.generateGenome(minValue, maxValue) : i.x2;
                    int x3 = isTrue(mutationChanceForOffspring) ? i.generateGenome(minValue, maxValue) : i.x3;
                    int x4 = isTrue(mutationChanceForOffspring) ? i.generateGenome(minValue, maxValue) : i.x4;
                    int x5 = isTrue(mutationChanceForOffspring) ? i.generateGenome(minValue, maxValue) : i.x5;

                    return new Equation(x1, x2, x3, x4, x5);
                })
                .collect(Collectors.toList());
    }

    private boolean isTrue(double mutationValue) {
        Random rand = new Random();
        return rand.nextDouble(1)+0 < mutationValue;
    }
}
