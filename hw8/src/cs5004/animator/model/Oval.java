package cs5004.animator.model;

/**
 * This class represents an oval shape.
 */
public class Oval extends AbstractShape {
  private ShapeType type;

  /**
   * Construct a new oval object.
   *
   * @param name name
   * @param pos  center coordinates
   * @param scl  X radius and Y radius
   * @param col  color
   * @param t1   the time when the AbstractShape appears
   * @param t2   the time when the AbstractShape disappears
   */
  public Oval(String name, float[] pos, float[] scl, float[] col, int t1, int t2) {
    super(name, pos, scl, col, t1, t2);
    this.type = ShapeType.OVAL;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }
}
