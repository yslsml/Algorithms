package sem6.graphalgorithms.coloring;


public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.generateRandomGraph(graph);
        System.out.println(graph);
        System.out.println("\nGreedy coloring algorithm: ");
        GreedyColoringAlgorithm.greedyAlgorithm(graph);

        System.out.println("\nDsatur coloring algorithm: ");
        DsaturColoringAlgorithm.dsaturAlgorithm(graph);

    }

}

