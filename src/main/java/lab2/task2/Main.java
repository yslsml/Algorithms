package lab2.task2;

import lab2.util.ArrayHandler;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {

        int[] array = ArrayHandler.createArray(10, 1, 30);
        System.out.println("\nArray of elements to insert: " + Arrays.toString(array));
        BinarySearchTree tree = new BinarySearchTree().insertArrayOfValuesInBinaryTree(array);

        System.out.println("\nLevel order traversal of a tree (BFS): ");
        tree.printLevelOrderBFS();

        System.out.println("\nTraversal in ascending order: " + tree.traversalInOrder().toString());
        System.out.println("\nTraversal in descending order: " + tree.traversalDescendingOrder().toString());
        System.out.println("\nTraversal pre order: " + tree.traversalPreOrder().toString());
        System.out.println("\nTraversal post order: " + tree.traversalPostOrder().toString());

        int kMinKey = 1;
        System.out.println("\n" + (kMinKey + 1) + " min key = " + tree.findKeyMin(kMinKey));

        tree.balanceTree();
        System.out.println("\nLevel order traversal of a tree (BFS) after balancing: ");
        tree.printLevelOrderBFS();


        System.out.println("\n===============ADDITIONAL===============");
        int[] array2 = ArrayHandler.createArray(10, 1, 30);
        System.out.println("\nArray of elements to insert: " + Arrays.toString(array2));
        BinarySearchTree tree2 = new BinarySearchTree().insertArrayOfValuesInBinaryTree(array2);

        System.out.println("\nLevel order traversal of a tree (BFS): ");
        tree2.printLevelOrderBFS();

        int newNodeValue = 8;
        System.out.println("\nAdding new node: " + newNodeValue);
        tree2.insertNode(newNodeValue);
        System.out.println("\nLevel order traversal of a tree (BFS): ");
        tree2.printLevelOrderBFS();

        System.out.println("\nDeleting node: " + newNodeValue);
        tree2.deleteNode(newNodeValue);
        System.out.println("\nLevel order traversal of a tree (BFS): ");
        tree2.printLevelOrderBFS();

        kMinKey = 4;
        System.out.println("\nValue to search ("+ (kMinKey + 1) + " min key): " + tree.findNodeByValue(tree.findKeyMin(kMinKey)));

        System.out.println("\nIs it true that this tree is a Binary Search Tree? : " + tree2.isBST());

    }
}
