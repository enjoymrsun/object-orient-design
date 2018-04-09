/**
 * This class represents a rectangle shape.
 */
public class Rectangle extends AbstractShape {

  private ShapeType type;

  /**
   * Construct a new rectangle object.
   *
   * @param name name
   * @param pos  bottom left corner coordinates
   * @param scl  width and height
   * @param col  color
   * @param t1   the time when the AbstractShape appears
   * @param t2   the time when the AbstractShape disappears
   */
  public Rectangle(char name, double[] pos, double[] scl, double[] col,
                   int t1, int t2) {
    super(name, pos, scl, col, t1, t2);
    this.type = ShapeType.RECTANGLE;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }
}
