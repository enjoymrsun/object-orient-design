package bst;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the BST implementation using integer as object.
 */
public class BSTTest {
  private BST<Integer> tree;
  private BST<Integer> emptytree;

  /**
   * Initialize an integer BST.
   */
  @Before
  public void setup() {
    tree = new BSTImpl();
    emptytree = new BSTImpl();

    tree.add(7);
    tree.add(3);
    tree.add(10);
    tree.add(1);
    tree.add(8);
    tree.add(12);
  }

  /**
   * Test add() method.
   */
  @Test
  public void testAdd() {
    emptytree.add(1);
    assertEquals(emptytree.preorder(), "1\n");

    tree.add(9);
    assertEquals(tree.preorder(), "7\n3\n1\n10\n8\n9\n12\n");
  }

  /**
   * Test getSize() method.
   */
  @Test
  public void testGetSize() {
    assertEquals(emptytree.getSize(), 0);
    assertEquals(tree.getSize(), 6);
  }

  /**
   * Test present() method.
   */
  @Test
  public void testPresent() {
    assertEquals(tree.present(7), true);
    assertEquals(tree.present(9), false);
  }

  /**
   * Test minimum() method.
   */
  @Test
  public void testMinimum() {
    assertEquals(emptytree.minimum(), null);
    assertEquals(tree.minimum().toString(), "1");
  }

  /**
   * Test preorder() method.
   */
  @Test
  public void testPreorder() {
    assertEquals(tree.preorder(), "7\n3\n1\n10\n8\n12\n");
  }

  /**
   * Test inorder() method.
   */
  @Test
  public void testInorder() {
    assertEquals(tree.inorder(), "1\n3\n7\n8\n10\n12\n");
  }

  /**
   * Test postorder() method.
   */
  @Test
  public void testPostorder() {
    assertEquals(tree.postorder(), "1\n3\n8\n12\n10\n7\n");
  }

  /**
   * Test getheight() method.
   */
  @Test
  public void testGetHeight() {
    assertEquals(tree.getHeight(), 3);
  }
}