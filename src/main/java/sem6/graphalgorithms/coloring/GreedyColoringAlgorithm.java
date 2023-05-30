package sem6.graphalgorithms.coloring;

import java.util.Arrays;

public class GreedyColoringAlgorithm {
    public static void greedyAlgorithm(Graph graph) {
        int[] result = new int[graph.verteces];
        Arrays.fill(result, -1);

        boolean[] colorAvailable = new boolean[graph.verteces];
        Arrays.fill(colorAvailable, true);

        for (int i = 0; i < graph.verteces; i++) {
            for (Integer vertex : graph.adjancencyList[i]) {
                if (result[vertex]!=-1) {
                    colorAvailable[result[vertex]] = false;
                }
            }
            int b;
            for (b = 0; b < colorAvailable.length; b++) {
                if(colorAvailable[b]) break;
            }
            result[i] = b;
            Arrays.fill(colorAvailable, true);
        }

        for (int i = 0; i < graph.verteces; i++){
            System.out.println("Vertex " + i + " - Color " + result[i]);
        }
    }
}
