package lab3.task5.algorithm;

import java.util.Arrays;

public class GaleShapleyAlgorithm {
    public GaleShapleyAlgorithm() {

    }

    public void moreEffectiveEmployeeForTask(int[][] workersPriorities, int[][] tasksPriorities) {
        int[] listForWorkers = new int[workersPriorities.length];
        int[] listForTasks = new int[tasksPriorities.length];
        fillArray(listForWorkers, listForTasks);
        while (!coverAllArray(listForTasks) && !coverAllArray(listForWorkers)) {
            for (int i = 0; i < workersPriorities.length; i++) {
                if (listForWorkers[i] == -1) {
                    if (listForTasks[workersPriorities[i][0]] == -1) {
                        listForTasks[workersPriorities[i][0]] = i;
                        listForWorkers[i] = workersPriorities[i][0];
                    } else {
                        int iterator = 0;
                        while (listForWorkers[i] == -1) {
                            if ((listForTasks[workersPriorities[i][iterator]] != -1)) {
                                if (getPosition(tasksPriorities[workersPriorities[i][iterator]], i) < getPosition(tasksPriorities[workersPriorities[i][iterator]], listForTasks[workersPriorities[i][iterator]])) {
                                    listForWorkers[listForTasks[workersPriorities[i][iterator]]] = -1;
                                    listForTasks[workersPriorities[i][iterator]] = i;
                                    listForWorkers[i] = workersPriorities[i][iterator];
                                }
                            } else {
                                listForTasks[workersPriorities[i][iterator]] = i;
                                listForWorkers[i] = workersPriorities[i][iterator];
                            }
                            iterator++;
                        }
                    }
                }
            }
        }
        System.out.println("\nA worker could be assigned to perform a task only if all the more interesting tasks for him were assigned to be performed by another, more efficient worker.");
        System.out.println("Each worker goes to this task: " + Arrays.toString(listForWorkers));
        System.out.println("\nA task is assigned to a worker only if all workers who are more effective at performing this task have been assigned to tasks that are more interesting to them.");
        System.out.println("Each task goes to this worker: " + Arrays.toString(listForTasks));
    }

    private int getPosition(int[] arr, int element) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == element) {
                return i;
            }
        }
        return -1;
    }

    private void fillArray(int[] arr1, int[] arr2) {
        Arrays.fill(arr1, -1);
        Arrays.fill(arr2, -1);
    }

    private boolean coverAllArray(int[] arr) {
        for (int element : arr) {
            if (element == -1) return false;
        }
        return true;
    }

}

