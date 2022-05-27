public class Main {
    public static void main(String[] args) {
        Tree<Integer> binTree = new Tree<>();
        binTree.add(10);
        binTree.add(11);
        binTree.add(9);
        binTree.printPrettyTree();
        binTree.add(4);
        binTree.printPrettyTree();
        binTree.delete(9);
        binTree.printPrettyTree();
        System.out.println(binTree.getHeight());
        System.out.println(binTree.getHeightByLog());
    }
}
