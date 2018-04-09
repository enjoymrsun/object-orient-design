/**
 * This interface represents a shape.
 */
public interface Shape {

  /**
   * Get the name of the shape.
   *
   * @return the name of the shape
   */
  char getName();

  /**
   * Get the type of the shape.
   *
   * @return the type of the shape
   */
  ShapeType getType();

  /**
   * Get the bottom left corner or center coordinates of the shape.
   *
   * @return the bottom left corner or center coordinates
   */
  double[] getPos();

  /**
   * Get the width/height or radius of the shape.
   *
   * @return the width/height or radius
   */
  double[] getScl();

  /**
   * Get the color of the shape.
   *
   * @return the color RGB
   */
  double[] getCol();

  /**
   * Get the time when the shape appears.
   *
   * @return the time when the shape appears.
   */
  int getT1();

  /**
   * Get the time when the shape disappears.
   *
   * @return the time when the shape disappears.
   */
  int getT2();

  /**
   * Change the shape's color.
   *
   * @param newCol the new color
   */
  void changeColor(double[] newCol);

  /**
   * Move the shape to a new position.
   *
   * @param newPos coordinates of the new position
   */
  void move(double[] newPos);

  /**
   * Scale the shape with new width/height/radius.
   *
   * @param newScl new width/height/radius
   */
  void scale(double[] newScl);
}
