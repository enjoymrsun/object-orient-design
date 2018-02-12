import java.util.NoSuchElementException;

/**
 * This class represents a Rectangle. A rectangle has a left down corner coordinates, a width and a
 * height.
 */
public class Rectangle {
  private int a;
  private int b;
  private int x;
  private int y;

  /**
   * Create and return a Rectangle object with the given coordinates of its lower left corner, its
   * width and its height in that order.
   *
   * @param a the 'x' coordinate of the lower left corner
   * @param b the 'y' coordinate of the lower left corner
   * @param x width of the rectangle
   * @param y height of the rectangle
   * @throws IllegalArgumentException if width or height is less than 0
   */
  public Rectangle(int a, int b, int x, int y) throws IllegalArgumentException {
    if (x < 0 || y < 0) {
      throw new IllegalArgumentException("invalid width or invalid height");
    }

    this.a = a;
    this.b = b;
    this.x = x;
    this.y = y;
  }

  /**
   * Return true if this rectangle overlaps with other, false otherwise.
   *
   * @param other the other Rectangle object used to do the overlap comparison
   * @return whether two Rectangles overlap or not
   */
  public boolean overlap(Rectangle other) {
    int[] res = computeIntersect(other);

    return res[2] >= 0 && res[3] >= 0;
  }

  /**
   * Compute and return the coordinates of the left corner and possible width and height of the
   * intersect rectangle, negative value for width and height if no intersection exists.
   *
   * @param other the other Rectangle object used to calculate the intersection
   * @return the 4 number of the possible intersect rectangle
   */
  private int[] computeIntersect(Rectangle other) {
    int newA = Math.max(a, other.a);
    int newB = Math.max(b, other.b);
    int newX = Math.min(a + x, other.a + other.x) - newA;
    int newY = Math.min(b + y, other.b + other.y) - newB;

    return new int[]{newA, newB, newX, newY};
  }

  /**
   * Return a Rectangle object that represents the intersection of this rectangle and the other
   * rectangle. If no intersection exists, it should throw a NoSuchElementException with a helpful
   * message.
   *
   * @param other the other Rectangle object used to calculate the intersection
   * @return the result Rectangle object
   * @throws NoSuchElementException if two Rectangles do not intersect with each other
   */
  public Rectangle intersect(Rectangle other) throws NoSuchElementException {
    int[] res = computeIntersect(other);

    if (res[2] < 0 || res[3] < 0) {
      throw new NoSuchElementException("These two Rectangles do not intersect with each other");
    }

    return new Rectangle(res[0], res[1], res[2], res[3]);
  }

  /**
   * Returns a Rectangle object that represents the union of this rectangle and the other
   * rectangle.
   *
   * @param other the other Rectangle object used to calculate the union
   * @return the smallest rectangle that contains both rectangles
   */
  public Rectangle union(Rectangle other) {
    int newA = Math.min(a, other.a);
    int newB = Math.min(b, other.b);
    int newX = Math.max(a + x, other.a + other.x) - newA;
    int newY = Math.max(b + y, other.b + other.y) - newB;

    return new Rectangle(newA, newB, newX, newY);
  }

  /**
   * Returns the string representation of the Rectangle object.
   *
   * @return the string representation of the Rectangle object
   */
  @Override
  public String toString() {
    return String.format("x:%d, y:%d, w:%d, h:%d", a, b, x, y);
  }
}
