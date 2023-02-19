package sem6.turingmachine.task1;

import java.util.Arrays;

import static sem6.turingmachine.task1.Alphabet.*;

public class Main {

    public static void main(String[] args) {

        String[] array = {space, b, b, c, b, c, b, c, space};
        String[] array2 = {space, b, b, c, a, c, b, c, space};

        System.out.println("\nInitial array: \n" + Arrays.toString(array));
        TuringMachine.turingMachine(array);
        System.out.println("The result of the program: \n" + Arrays.toString(array));

        System.out.println("\nInitial array: \n" + Arrays.toString(array2));
        TuringMachine.turingMachine(array2);
        System.out.println("The result of the program: \n" + Arrays.toString(array2));

    }

}
