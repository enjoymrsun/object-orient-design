package bst;

/**
 * This class represents an element node in the binary search tree.
 */
public class BSTADTElement<T extends Comparable<T>> implements BSTADT<T> {

  private T object;
  private BSTADT<T> left;
  private BSTADT<T> right;

  /**
   * Construct an element node in the binary search tree.
   *
   * @param obj   the object contained by this element node
   * @param left  the nodes in its left subtree
   * @param right the nodes in its right subtree
   */
  public BSTADTElement(T obj, BSTADT<T> left, BSTADT<T> right) {
    this.object = obj;
    this.left = left;
    this.right = right;
  }

  @Override
  public BSTADT<T> add(T obj) {
    if (this.object.compareTo(obj) > 0) {
      this.left = this.left.add(obj);
      return this;
    } else if (this.object.compareTo(obj) < 0) {
      this.right = this.right.add(obj);
      return this;
    } else {
      return this;
    }
  }

  @Override
  public int getSize() {
    return 1 + this.left.getSize() + this.right.getSize();
  }

  @Override
  public boolean present(T obj) {
    if (this.object.compareTo(obj) > 0) {
      return this.left.present(obj);
    } else if (this.object.compareTo(obj) < 0) {
      return this.right.present(obj);
    } else {
      return true;
    }
  }

  @Override
  public T minimum() {
    if (this.left.getClass().equals(BSTADTEmpty.class)) {
      return this.object;
    } else {
      return this.left.minimum();
    }
  }

  @Override
  public String preorder() {
    return this.object.toString() + "\n" + this.left.preorder() + this.right.preorder();
  }

  @Override
  public String inorder() {
    return this.left.inorder() + this.object.toString() + "\n" + this.right.inorder();
  }

  @Override
  public String postorder() {
    return this.left.postorder() + this.right.postorder() + this.object.toString() + "\n";
  }

  @Override
  public int getHeight() {
    return 1 + Math.max(this.left.getHeight(), this.right.getHeight());
  }
}
