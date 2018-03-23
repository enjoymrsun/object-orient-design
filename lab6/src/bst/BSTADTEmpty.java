package bst;

/**
 * This class represents an empty node in the binary search tree.
 */
public class BSTADTEmpty<T extends Comparable<T>> implements BSTADT<T> {

  @Override
  public BSTADT<T> add(T obj) {
    return new BSTADTElement<>(obj, this, this);
  }

  @Override
  public int getSize() {
    return 0;
  }

  @Override
  public boolean present(T obj) {
    return false;
  }

  @Override
  public T minimum() {
    return null;
  }

  @Override
  public String preorder() {
    return "";
  }

  @Override
  public String inorder() {
    return "";
  }

  @Override
  public String postorder() {
    return "";
  }

  @Override
  public int getHeight() {
    return 0;
  }
}
