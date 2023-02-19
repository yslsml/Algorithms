package sem6.turingmachine.task1;

import java.util.Objects;

import static sem6.turingmachine.task1.Alphabet.*;

public class TuringMachine {

    public static void turingMachine(String[] array) {
        State state = State.q1;
        int i = 0;

        while (state != State.q0) {
            if (state == State.q1) {
                if (Objects.equals(array[i], space)) {
                    array[i] = space;
                    state = State.q1;
                    i++;
                    if (i == array.length) {
                        state = State.q0;
                    }
                } else if (Objects.equals(array[i], b)) {
                    array[i] = c;
                    state = State.q1;
                    i++;
                } else if (Objects.equals(array[i], c)) {
                    array[i] = c;
                    state = State.q1;
                    i++;
                } else if (Objects.equals(array[i], a)) {
                    array[i] = a;
                    state = State.q2;
                    i++;
                }
            } else if (state == State.q2) {
                if (Objects.equals(array[i], c)) {
                    array[i] = space;
                    state = State.q2;
                    i++;
                } else if (Objects.equals(array[i], b)) {
                    array[i] = space;
                    state = State.q2;
                    i++;
                } else if (Objects.equals(array[i], space)) {
                    array[i] = space;
                    state = State.q2;
                    i--;
                } else if (Objects.equals(array[i], a)) {
                    array[i] = a;
                    state = State.q3;
                    i--;
                }
            } else if (state == State.q3) {
                if (Objects.equals(array[i], c)) {
                    array[i] = space;
                    state = State.q3;
                    i--;
                } else if (Objects.equals(array[i], space)) {
                    array[i] = space;
                    state = State.q0;
                }
            }
        }
    }

}
