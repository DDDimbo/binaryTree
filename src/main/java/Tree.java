import java.util.*;

public class Tree<T extends Number & Comparable<? super T>> implements TreeInterface<T> {
    static class TreeNode<E> {
        E value;
        TreeNode<E> leftChild;
        TreeNode<E> rightChild;

        final List<TreeNode<E>> children = new ArrayList<>();
        int height;

        public TreeNode(E value) {
            this.value = value;
        }


        void calcHeight() {
            height = 0;
            if (leftChild != null) {
                leftChild.calcHeight();
                height = Math.max(height, leftChild.height + 1);
            }
            if (rightChild != null) {
                rightChild.calcHeight();
                height = Math.max(height, rightChild.height + 1);
            }
        }

        private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
            if (rightChild != null) {
                rightChild.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(value.toString()).append("\n");
            if (leftChild != null) {
                leftChild.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
            }
            return sb;
        }

        @Override
        public String toString() {
            return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
        }


        public void printNode() { // Вывод значения узла в консоль
            System.out.println(" Выбранный узел имеет значение :" + value);
        }

        public E getValue() {
            return this.value;
        }

        public void setValue(final E value) {
            this.value = value;
        }

        public TreeNode<E> getLeftChild() {
            return this.leftChild;
        }

        public void setLeftChild(final TreeNode<E> leftChild) {
            this.leftChild = leftChild;
        }

        public TreeNode<E> getRightChild() {
            return this.rightChild;
        }

        public void setRightChild(final TreeNode<E> rightChild) {
            this.rightChild = rightChild;
        }

    }


    private TreeNode<T> root;

    private int countOfElements = 0;


    @Override
    public void printPrettyTree() {
        System.out.println(root.toString());
    }


    @Override
    public int getHeight() {
        root.calcHeight();
        return root.height;
    }

    @Override
    public int getHeightByLog() {
        System.out.println(Math.round(Math.log10(countOfElements) / Math.log10(2)));
        return root.height;
    }


    @Override
    public void add(T newValue) {
        countOfElements++;
        root = addRecursive(root, newValue);
    }

    private TreeNode<T> addRecursive(TreeNode<T> node, T newValue) {
        if (node == null) {
            return new TreeNode<>(newValue);
        }
        if (newValue.compareTo(node.value) < 0) {
            node.leftChild = addRecursive(node.leftChild, newValue);
        } else if (newValue.compareTo(node.value) > 0) {
            node.rightChild = addRecursive(node.rightChild, newValue);
        } else {
            return node;  // value already exists
        }

        return node;
    }


    @Override
    public boolean contains(T value) {
        return containsRecursive(root, value);
    }

    private boolean containsRecursive(TreeNode<T> current, T value) {
        if (current == null) {
            return false;
        }
        if (value.compareTo(current.value) == 0) {
            return true;
        }
        return value.compareTo(current.value) < 0
                ? containsRecursive(current.leftChild, value)
                : containsRecursive(current.rightChild, value);
    }


    @Override
    public void delete(T value) {
        countOfElements--;
        root = deleteRecursive(root, value);
    }

    private T findSmallestValue(TreeNode<T> root) {
        return root.leftChild == null ? root.value : findSmallestValue(root.leftChild);
    }

    private TreeNode<T> deleteRecursive(TreeNode<T> current, T value) {
        if (current == null) {
            return null;
        }

        if (value.compareTo(current.value) == 0) {
            if (current.leftChild == null && current.rightChild == null) {
                return null;
            }
            if (current.rightChild == null) {
                return current.leftChild;
            }
            if (current.leftChild == null) {
                return current.rightChild;
            }
            T smallestValue = findSmallestValue(current.rightChild);
            current.value = smallestValue;
            current.rightChild = deleteRecursive(current.rightChild, smallestValue);
            return current;
        }
        if (value.compareTo(current.value) < 0) {
            current.leftChild = deleteRecursive(current.leftChild, value);
            return current;
        }
        current.rightChild = deleteRecursive(current.rightChild, value);
        return current;
    }

    @Override
    public void traverseInOrder(TreeNode<T> node) {
        if (node != null) {
            traverseInOrder(node.leftChild);
            System.out.print(" " + node.value);
            traverseInOrder(node.rightChild);
        }
    }

    @Override
    public void traversePreOrder(TreeNode<T> node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.leftChild);
            traversePreOrder(node.rightChild);
        }
    }

    @Override
    public void traversePostOrder(TreeNode<T> node) {
        if (node != null) {
            traversePostOrder(node.leftChild);
            traversePostOrder(node.rightChild);
            System.out.print(" " + node.value);
        }
    }

    @Override
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {

            TreeNode<T> node = nodes.remove();

            System.out.print(" " + node.value);

            if (node.leftChild != null) {
                nodes.add(node.leftChild);
            }

            if (node.rightChild != null) {
                nodes.add(node.rightChild);
            }
        }
    }
}
