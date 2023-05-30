package sem6.graphalgorithms.tsp;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Town> townList = Town.generateTowns();
        Collections.shuffle(townList);

        System.out.println("\nTravelling in default order: " + townList);
        System.out.println("\nTravel distance: " +  TSPAlgorithm.calculateTravelDistance(townList));

        TSPAlgorithm.algorithmOfTwoReplacement(townList);
        System.out.println("\n\nTravelling using TSP algorithm: " + townList);
        System.out.println("\nTravel distance: " +  TSPAlgorithm.calculateTravelDistance(townList));

    }

}

