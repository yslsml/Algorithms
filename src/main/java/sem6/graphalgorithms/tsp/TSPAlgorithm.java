package sem6.graphalgorithms.tsp;

import java.util.List;

public class TSPAlgorithm {

    public static double calculateTravelDistance(List<Town> towns) {
        double travelDistance = 0;
        for(int i = 0; i < towns.size()-1; i++) {
            travelDistance +=towns.get(i).distance(towns.get(i+1));
        }
        travelDistance += towns.get(0).distance(towns.get(towns.size()-1));
        return travelDistance;
    }

    public static void algorithmOfTwoReplacement(List<Town> towns) {
        boolean flag = true;
        while(flag) {
            flag = false;
            for (int i = 0; i < towns.size() - 1; i++) {
                for (int j = i + 1; j < towns.size(); j++) {
                    double oldDistance = towns.get(i).distance(towns.get(i + 1)) + towns.get(j).distance(towns.get( (j + 1) % towns.size() ));
                    double newDistance = towns.get(i).distance(towns.get(j)) + towns.get(i + 1).distance(towns.get( (j + 1) %  towns.size() ));
                    if (oldDistance > newDistance) {
                        Town townTmp = towns.get(i + 1);
                        towns.set(i + 1, towns.get((j)));
                        towns.set(j, townTmp);
                        flag = true;
                    }
                }
            }
        }
    }
}
