public interface TreeInterface<T> {

    void add(T newValue);

    void printPrettyTree();

    int getHeight();

    int getHeightByLog();

    boolean contains(T value);

    //по глубине
    void traverseInOrder(Tree.TreeNode<T> node);

    void traversePreOrder(Tree.TreeNode<T> node);

    void traversePostOrder(Tree.TreeNode<T> node);

    // по ширине
    void traverseLevelOrder();

    void delete(T value);
}
