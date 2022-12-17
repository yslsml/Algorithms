package lab2.task2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinarySearchTree {

    public TreeNode rootNode;

    public BinarySearchTree() {
        rootNode = null;
    }

    public void insertNode(int value) {
        TreeNode newNode = new TreeNode();
        newNode.setValue(value);
        if (rootNode == null) {
            rootNode = newNode;
        } else {
            TreeNode currentNode = rootNode;
            TreeNode parentNode;
            while (true) {
                parentNode = currentNode;
                if (value == currentNode.getValue()) {
                    return;
                } else if (value < currentNode.getValue()) {
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) {
                        parentNode.setLeftChild(newNode);
                        return;
                    }
                } else {
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) {
                        parentNode.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    public BinarySearchTree insertArrayOfValuesInBinaryTree(int[] array) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        for (int element : array) {
            binarySearchTree.insertNode(element);
        }
        return binarySearchTree;
    }

    public void deleteNode(int value) {
        TreeNode currentNode = rootNode;
        TreeNode parentNode = rootNode;
        boolean isLeftChild = true;
        while (currentNode.getValue() != value) {
            parentNode = currentNode;
            if (value < currentNode.getValue()) {
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            } else {
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) return;
        }

        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) {
            if (currentNode == rootNode) rootNode = null;
            else if (isLeftChild) parentNode.setLeftChild(null);
            else parentNode.setRightChild(null);
        } else if (currentNode.getRightChild() == null) {
            if (currentNode == rootNode) rootNode = currentNode.getLeftChild();
            else if (isLeftChild) parentNode.setLeftChild(currentNode.getLeftChild());
            else parentNode.setRightChild(currentNode.getLeftChild());
        } else if (currentNode.getLeftChild() == null) {
            if (currentNode == rootNode) rootNode = currentNode.getRightChild();
            else if (isLeftChild) parentNode.setLeftChild(currentNode.getRightChild());
            else parentNode.setRightChild(currentNode.getRightChild());
        } else {
            TreeNode heir = getHeirNode(currentNode);
            if (currentNode == rootNode) rootNode = heir;
            else if (isLeftChild) parentNode.setLeftChild(heir);
            else parentNode.setRightChild(heir);
        }
    }

    private TreeNode getHeirNode(TreeNode node) {
        TreeNode parentNode = node;
        TreeNode heirNode = node;
        TreeNode currentNode = node.getRightChild();
        while (currentNode != null) {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.getLeftChild();
        }
        if (heirNode != node.getRightChild()) {
            parentNode.setLeftChild(heirNode.getRightChild());
            heirNode.setRightChild(node.getRightChild());
        }
        return heirNode;
    }

    public TreeNode findNodeByValue(int value) {
        TreeNode currentNode = rootNode;
        boolean isLeft = false, isRoot = true;
        while (currentNode.getValue() != value) {
            if (value < currentNode.getValue()) {
                currentNode = currentNode.getLeftChild();
                isRoot = false;
                isLeft = true;
            } else { //движение вправо
                currentNode = currentNode.getRightChild();
                isRoot = false;
                isLeft = false;
            }
            if (currentNode == null) {
                return null;
            }
        }
        if (isRoot) System.out.println("It is a root node.");
        if (isLeft) System.out.println("It is a left node.");
        else System.out.println("It is a right node.");
        return currentNode;
    }

    public int findKeyMin(int key) {
        ArrayList<Integer> list = this.traversalInOrder();
        return list.get(key);
    }

    public ArrayList<Integer> traversalInOrder() {
        ArrayList<Integer> listOfNodes = new ArrayList<Integer>();
        traversalInOrderRecursive(rootNode, listOfNodes);
        return listOfNodes;
    }

    public void traversalInOrderRecursive(TreeNode node, ArrayList<Integer> arrayOfNodes) {
        if (node != null) {
            traversalInOrderRecursive(node.getLeftChild(), arrayOfNodes);
            arrayOfNodes.add(node.getValue());
            traversalInOrderRecursive(node.getRightChild(), arrayOfNodes);
        }
    }

    public ArrayList<Integer> traversalDescendingOrder() {
        ArrayList<Integer> listOfNodes = new ArrayList<Integer>();
        traversalDescendingOrderRecursive(rootNode, listOfNodes);
        return listOfNodes;
    }

    public void traversalDescendingOrderRecursive(TreeNode node, ArrayList<Integer> arrayOfNodes) {
        if (node != null) {
            traversalDescendingOrderRecursive(node.getRightChild(), arrayOfNodes);
            arrayOfNodes.add(node.getValue());
            traversalDescendingOrderRecursive(node.getLeftChild(), arrayOfNodes);
        }
    }

    public ArrayList<Integer> traversalPreOrder() {
        ArrayList<Integer> listOfNodes = new ArrayList<Integer>();
        traversalPreOrderRecursive(rootNode, listOfNodes);
        return listOfNodes;
    }

    public void traversalPreOrderRecursive(TreeNode node, ArrayList<Integer> arrayOfNodes) {
        if (node != null) {
            arrayOfNodes.add(node.getValue());
            traversalPreOrderRecursive(node.getLeftChild(), arrayOfNodes);
            traversalPreOrderRecursive(node.getRightChild(), arrayOfNodes);
        }
    }

    public ArrayList<Integer> traversalPostOrder() {
        ArrayList<Integer> listOfNodes = new ArrayList<Integer>();
        traversalPostOrderRecursive(rootNode, listOfNodes);
        return listOfNodes;
    }

    public void traversalPostOrderRecursive(TreeNode node, ArrayList<Integer> arrayOfNodes) {
        if (node != null) {
            traversalPostOrderRecursive(node.getLeftChild(), arrayOfNodes);
            traversalPostOrderRecursive(node.getRightChild(), arrayOfNodes);
            arrayOfNodes.add(node.getValue());
        }
    }

    public void printLevelOrderBFS() {
        TreeNode root = this.rootNode;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.getValue() + " ");
            if (tempNode.getLeftChild() != null) {
                queue.add(tempNode.getLeftChild());
            }
            if (tempNode.getRightChild() != null) {
                queue.add(tempNode.getRightChild());
            }
        }
        System.out.println();
    }

    public void balanceTree() {
        ArrayList<Integer> list = this.traversalInOrder();
        this.rootNode = null;
        this.balanceTreeRecursive(list);
    }

    public void balanceTreeRecursive(List<Integer> list) {
        if (list.isEmpty()) return;
        int middleKey = list.size() / 2;
        this.insertNode(list.get(middleKey));
        this.balanceTreeRecursive(list.subList(0, middleKey));
        this.balanceTreeRecursive(list.subList(middleKey + 1, list.size()));
    }

    public boolean isBST() {
        return isBSTRecursive(rootNode, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public boolean isBSTRecursive(TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.getValue() < min || node.getValue() > max) return false;
        return isBSTRecursive(node.getLeftChild(), min, node.getValue() - 1) && isBSTRecursive(node.getRightChild(), node.getValue() + 1, max);
    }

}



