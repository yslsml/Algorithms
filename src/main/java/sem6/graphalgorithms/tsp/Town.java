package sem6.graphalgorithms.tsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

class Town {
    private double xCoordinate;
    private double yCoordinate;
    public Town(double xCoordinate, double yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public double distance(Town anotherTown) {
        return Math.sqrt(Math.pow(this.xCoordinate - anotherTown.xCoordinate,2)+Math.pow(this.yCoordinate - anotherTown.yCoordinate,2));
    }

    public static List<Town> generateTowns() {
        List<Town> townList = new ArrayList<>();
        Random random = new Random();
        while (townList.size() < 10) {
            int x = random.nextInt(21);
            int y = random.nextInt(21);
            Town town = new Town(x, y);
            if (!townList.contains(town)) {
                townList.add(town);
            }
        }
        return townList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Town)) return false;
        Town town = (Town) o;
        return Double.compare(town.xCoordinate, xCoordinate) == 0 && Double.compare(town.yCoordinate, yCoordinate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return "\nTown{" +
                "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }
}
