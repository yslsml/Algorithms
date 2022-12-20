package lab3.task4.algorithm;

import java.util.ArrayList;

public class HungarianAlgorithm {

    private int numberOfRows;
    private int numberOfCols;

    private boolean[][] primes;
    private boolean[][] stars;
    private boolean[] rowsCovered;
    private boolean[] colsCovered;
    private float[][] costs;

    public HungarianAlgorithm(float theCosts[][]) {
        float[][] copy = copy(theCosts);
        costs = copy;
        numberOfRows = costs.length;
        numberOfCols = costs[0].length;

        primes = new boolean[numberOfRows][numberOfCols];
        stars = new boolean[numberOfRows][numberOfCols];

        rowsCovered = new boolean[numberOfRows];
        colsCovered = new boolean[numberOfCols];
        for (int i = 0; i < numberOfRows; i++) {
            rowsCovered[i] = false;
        }
        for (int j = 0; j < numberOfCols; j++) {
            colsCovered[j] = false;
        }

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                primes[i][j] = false;
                stars[i][j] = false;
            }
        }
    }

    public int[][] findOptimalAssignment() {
        subtractMinElementFromRowAndColumn();

        findStars(); // find first zero in column - O(n^2)
        resetCovered(); // O(n);
        coverStarredZeroCols(); // O(n^2)

        while (!allColsCovered()) {
            int[] primedLocation = primeUncoveredZero(); // O(n^2)

            // It's possible that we couldn't find a zero to prime, so we have to induce some zeros so we can find one to prime
            if (primedLocation[0] == -1) {
                minUncoveredRowsCols(); // O(n^2)
                primedLocation = primeUncoveredZero(); // O(n^2)
            }

            // is there a starred 0 in the primed zeros row?
            int primedRow = primedLocation[0];
            int starCol = findStarColInRow(primedRow);
            if (starCol != -1) {
                // cover the row of the primedLocation and uncover the star column
                rowsCovered[primedRow] = true;
                colsCovered[starCol] = false;
            } else { // otherwise we need to find an augmenting path and start over.
                increasePathStartingAtPrime(primedLocation);
                resetCovered();
                resetPrimes();
                coverStarredZeroCols();
            }
        }

        int [][] answer = starsToAssignments();
        return answer; // O(n^2)

    }


//     the starred 0's in each column are the assignments - O(n^2)
    public int[][] starsToAssignments() {
        int[][] toReturn = new int[numberOfCols][];
        for (int j = 0; j < numberOfCols; j++) {
            toReturn[j] = new int[] {
                    this.findStarRowInCol(j), j
            }; // O(n)
        }
        return toReturn;
    }


    public static int getOptimalSum(float[][]matrix, int[][]array) {
        int sum = 0;
        for (int i=0; i<array.length; i++) {
            int firstIndex = array[i][0];
            int secondIndex = array[i][1];
            sum += matrix[firstIndex][secondIndex];
        }
        return sum;
    }



//    resets prime information
    public void resetPrimes() {
        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfCols; j++) {
                primes[i][j] = false;
            }
        }
    }


//    resets covered information, O(n)
    public void resetCovered() {
        for (int i = 0; i < numberOfRows; i++) {
            rowsCovered[i] = false;
        }
        for (int j = 0; j < numberOfCols; j++) {
            colsCovered[j] = false;
        }
    }


// get the first zero in each column, star it if there isn't already a star in that row
// cover the row and column of the star made, and continue to the next column
// O(n^2)
    public void findStars() {
        boolean[] rowStars = new boolean[numberOfRows];
        boolean[] colStars = new boolean[numberOfCols];

        for (int i = 0; i < numberOfRows; i++) {
            rowStars[i] = false;
        }
        for (int j = 0; j < numberOfCols; j++) {
            colStars[j] = false;
        }

        for (int j = 0; j < numberOfCols; j++) {
            for (int i = 0; i < numberOfRows; i++) {
                if (costs[i][j] == 0 && !rowStars[i] && !colStars[j]) {
                    stars[i][j] = true;
                    rowStars[i] = true;
                    colStars[j] = true;
                    break;
                }
            }
        }
    }

//    sets the columns covered if they contain starred zeros - O(n^2)
    private void coverStarredZeroCols() {
        for (int j = 0; j < numberOfCols; j++) {
            colsCovered[j] = false;
            for (int i = 0; i < numberOfRows; i++) {
                if (stars[i][j]) {
                    colsCovered[j] = true;
                    break;
                }
            }
        }
    }


//    Finds the minimum uncovered value, and adds it to all the covered rows then
//    subtracts it from all the uncovered columns. This results in a cost matrix with
//    at least one more zero.
    private void minUncoveredRowsCols() {
        // find min uncovered value
        float minUncovered = Float.MAX_VALUE;
        for (int i = 0; i < numberOfRows; i++) {
            if (!rowsCovered[i]) {
                for (int j = 0; j < numberOfCols; j++) {
                    if (!colsCovered[j]) {
                        if (costs[i][j] < minUncovered) {
                            minUncovered = costs[i][j];
                        }
                    }
                }
            }
        }

        // add that value to all the COVERED rows.
        for (int i = 0; i < numberOfRows; i++) {
            if (rowsCovered[i]) {
                for (int j = 0; j < numberOfCols; j++) {
                    costs[i][j] = costs[i][j] + minUncovered;

                }
            }
        }

        // subtract that value from all the UNcovered columns
        for (int j = 0; j < numberOfCols; j++) {
            if (!colsCovered[j]) {
                for (int i = 0; i < numberOfRows; i++) {
                    costs[i][j] = costs[i][j] - minUncovered;
                }
            }
        }
    }


//    Finds an uncovered zero, primes it, and returns an array
//    describing the row and column of the newly primed zero.
//    If no uncovered zero could be found, returns -1 in the indices. - O(n^2)
    private int[] primeUncoveredZero() {
        int[] location = new int[2];

        for (int i = 0; i < numberOfRows; i++) {
            if (!rowsCovered[i]) {
                for (int j = 0; j < numberOfCols; j++) {
                    if (!colsCovered[j]) {
                        if (costs[i][j] == 0) {
                            primes[i][j] = true;
                            location[0] = i;
                            location[1] = j;
                            return location;
                        }
                    }
                }
            }
        }

        location[0] = -1;
        location[1] = -1;
        return location;
    }

//    Starting at a given primed location[0=row,1=col], we find an augmenting path
//    consisting of a primed , starred , primed , ..., primed. (note that it begins and ends with a prime)
//    We do this by starting at the location, going to a starred zero in the same column, then going to a primed zero in
//    the same row, etc, until we get to a prime with no star in the column.  - O(n^2)
    private void increasePathStartingAtPrime(int[] location) {
        // Make the arraylists sufficiently large to begin with
        ArrayList < int[] > primeLocations = new ArrayList < int[] > (numberOfRows + numberOfCols);
        ArrayList < int[] > starLocations = new ArrayList < int[] > (numberOfRows + numberOfCols);
        primeLocations.add(location);

        int currentRow = location[0];
        int currentCol = location[1];
        while (true) { // add stars and primes in pairs
            int starRow = findStarRowInCol(currentCol);
            // at some point we won't be able to find a star. if this is the case, break.
            if (starRow == -1) {
                break;
            }
            int[] starLocation = new int[] {
                    starRow, currentCol
            };
            starLocations.add(starLocation);
            currentRow = starRow;

            int primeCol = findPrimeColInRow(currentRow);
            int[] primeLocation = new int[] {
                    currentRow, primeCol
            };
            primeLocations.add(primeLocation);
            currentCol = primeCol;
        }

        unStarLocations(starLocations);
        starLocations(primeLocations);
    }

//    Given an arraylist of  locations, star them
    private void starLocations(ArrayList< int[] > locations) {
        for (int k = 0; k < locations.size(); k++) {
            int[] location = locations.get(k);
            int row = location[0];
            int col = location[1];
            stars[row][col] = true;
        }
    }

//    Given an arraylist of starred locations, unstar them
    private void unStarLocations(ArrayList < int[] > starLocations) {
        for (int k = 0; k < starLocations.size(); k++) {
            int[] starLocation = starLocations.get(k);
            int row = starLocation[0];
            int col = starLocation[1];
            stars[row][col] = false;
        }
    }


//    Given a row index, finds a column with a prime. returns -1 if this isn't possible.
    private int findPrimeColInRow(int theRow) {
        for (int j = 0; j < numberOfCols; j++) {
            if (primes[theRow][j]) {
                return j;
            }
        }
        return -1;
    }

//    Given a column index, finds a row with a star. returns -1 if this isn't possible.
    public int findStarRowInCol(int theCol) {
        for (int i = 0; i < numberOfRows; i++) {
            if (stars[i][theCol]) {
                return i;
            }
        }
        return -1;
    }

    public int findStarColInRow(int theRow) {
        for (int j = 0; j < numberOfCols; j++) {
            if (stars[theRow][j]) {
                return j;
            }
        }
        return -1;
    }

//    Looks at the colsCovered array, and returns true if all entries are true, false otherwise
    private boolean allColsCovered() {
        for (int j = 0; j < numberOfCols; j++) {
            if (!colsCovered[j]) {
                return false;
            }
        }
        return true;
    }

    private void subtractMinElementFromRowAndColumn() {
        for (int i = 0; i < numberOfRows; i++) {
            float rowMin = Float.MAX_VALUE;
            for (int j = 0; j < numberOfCols; j++) {
                if (costs[i][j] < rowMin) {
                    rowMin = costs[i][j];
                }
            }
            for (int j = 0; j < numberOfCols; j++) {
                costs[i][j] = costs[i][j] - rowMin;
            }
        }

        for (int j = 0; j < numberOfCols; j++) {
            float colMin = Float.MAX_VALUE;
            for (int i = 0; i < numberOfRows; i++) {
                if (costs[i][j] < colMin) {
                    colMin = costs[i][j];
                }
            }
            for (int i = 0; i < numberOfRows; i++) {
                costs[i][j] = costs[i][j] - colMin;
            }
        }
    }

    public static float[][] copy(float[][] src) {
        if (src == null) {
            return null;
        }

        float[][] copy = new float[src.length][];
        for (int i = 0; i < src.length; i++) {
            copy[i] = src[i].clone();
        }

        return copy;
    }

}
