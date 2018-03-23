package bst;

/**
 * This interface represents a binary search tree node.
 */
public interface BSTADT<T> {

  /**
   * Inserts an object in the tree.
   *
   * @param obj the object to be added
   * @return the result tree
   */
  BSTADT<T> add(T obj);

  /**
   * Return the size of this tree (the number of elements in this tree).
   *
   * @return the size of this tree
   */
  int getSize();

  /**
   * Return true if this object is present in the tree, false otherwise.
   *
   * @param obj the object we are looking for
   * @return whether this object is present in the tree
   */
  boolean present(T obj);

  /**
   * Returns the smallest object in the tree, and null if the tree is empty.
   *
   * @return the smallest object in the tree
   */
  T minimum();

  /**
   * Returns a string with the data in the node in Pre-order traversal.
   *
   * @return a string with the data in the node in Pre-order traversal
   */
  String preorder();

  /**
   * Returns a string with the data in the node in in-order traversal.
   *
   * @return a string with the data in the node in in-order traversal
   */
  String inorder();

  /**
   * Returns a string with the data in the node in post-order traversal.
   *
   * @return a string with the data in the node in post-order traversal
   */
  String postorder();

  /**
   * Return the height of this tree.
   *
   * @return the height of this tree
   */
  int getHeight();
}
