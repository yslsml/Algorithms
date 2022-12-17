package lab2.task2;

public class TreeNode {
    private int value;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(TreeNode rightChild) {
        this.rightChild = rightChild;
    }

    public void printNode() {
        System.out.println(" Выбранный узел имеет значение :" + value);
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", leftChild=" + leftChild +
                ", rightChild=" + rightChild +
                '}';
    }
}
