package sem6.turingmachine.task2;

import java.util.Arrays;

import static sem6.turingmachine.task2.Alphabet.*;

public class Main {

    /**
     * Г={ | }. Считая слово P записью положительного числа в единичной
     * системе счисления, уменьшить это число на 1.
     */

    public static void main(String[] args) {

        String[] array = {space, one, one, one, one, space, one, one, space, one, one, one, space, one , space};

        System.out.println("\nInitial array: \n" + Arrays.toString(array));
        TuringMachine.turingMachine(array);
        System.out.println("The result of the program: \n" + Arrays.toString(array));

    }

}
