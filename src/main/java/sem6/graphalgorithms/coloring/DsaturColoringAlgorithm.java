package sem6.graphalgorithms.coloring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static sem6.graphalgorithms.coloring.Graph.getMaxSaturation;

public class DsaturColoringAlgorithm {
    public static void dsaturAlgorithm(Graph graph) {
        int[] saturationDegrees = new int[graph.verteces];
        Arrays.fill(saturationDegrees, 0);
        List<Integer> fillingNodes = new ArrayList<>();
        int[] result = new int[graph.verteces];
        Arrays.fill(result, -1);
        boolean[] colorAvailable = new boolean[graph.verteces];
        Arrays.fill(colorAvailable, true);
        while (fillingNodes.size() < graph.verteces) {
            int maxSaturIndex = getMaxSaturation(saturationDegrees);
            for (Integer vertex : graph.adjancencyList[maxSaturIndex]) {
                if (result[vertex]!=-1) {
                    colorAvailable[result[vertex]] = false;
                }
            }
            int b;
            for (b = 0; b < colorAvailable.length; b++) {
                if(colorAvailable[b]) break;
            }
            result[maxSaturIndex] = b;
            Arrays.fill(colorAvailable, true);
            fillingNodes.add(maxSaturIndex);
            saturationDegrees[maxSaturIndex] = -1;
            for (Integer vertex : graph.adjancencyList[maxSaturIndex]) {
                if (saturationDegrees[vertex]!=-1) saturationDegrees[vertex] += 1;
            }
        }

        for (int i = 0; i < graph.verteces; i++){
            System.out.println("Vertex " + i + " - Color " + result[i]);
        }
    }

}
