package bst;

/**
 * This is the implementation of the binary search tree interface.
 */
public class BSTImpl<T extends Comparable<T>> implements BST<T> {

  private BSTADT<T> root;

  /**
   * Construct a BSTImpl and initiates it with an empty node.
   */
  public BSTImpl() {
    root = new BSTADTEmpty<>();
  }

  @Override
  public void add(T obj) {
    root = root.add(obj);
  }

  @Override
  public int getSize() {
    return root.getSize();
  }

  @Override
  public boolean present(T obj) {
    return root.present(obj);
  }

  @Override
  public T minimum() {
    return root.minimum();
  }

  @Override
  public String preorder() {
    return root.preorder();
  }

  @Override
  public String inorder() {
    return root.inorder();
  }

  @Override
  public String postorder() {
    return root.postorder();
  }

  @Override
  public int getHeight() {
    return root.getHeight();
  }
}
