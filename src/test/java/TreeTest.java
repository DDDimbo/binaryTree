import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {

    private static Tree<Integer> binTree;

    @BeforeEach
    void beforeEach(){
        binTree = new Tree<>();
    }

    @Test
    void add() {
        binTree.add(10);
        assertEquals(binTree.getHeight(), 0);

        binTree.add(11);
        assertEquals(binTree.getHeight(), 1);
        binTree.add(8);
        assertEquals(binTree.getHeight(), 1);
    }

    @Test
    void containsNode() {
        binTree.add(10);
        assertTrue(binTree.contains(10));
    }

    @Test
    void delete() {
        binTree.add(10);
        binTree.add(11);
        binTree.add(9);
        binTree.add(40);
        assertEquals(binTree.getHeight(), 2);
        binTree.delete(40);
        assertEquals(binTree.getHeight(), 1);
    }

}