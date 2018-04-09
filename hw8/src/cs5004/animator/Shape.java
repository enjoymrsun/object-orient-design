package cs5004.animator;

/**
 * This interface represents a shape.
 */
public interface Shape {

  /**
   * Get the name of the shape.
   *
   * @return the name of the shape
   */
  String getName();

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
  float[] getPos();

  /**
   * Get the width/height or radius of the shape.
   *
   * @return the width/height or radius
   */
  float[] getScl();

  /**
   * Get the color of the shape.
   *
   * @return the color RGB
   */
  float[] getCol();

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
  void changeColor(float[] newCol);

  /**
   * Move the shape to a new position.
   *
   * @param newPos coordinates of the new position
   */
  void move(float[] newPos);

  /**
   * Scale the shape with new width/height/radius.
   *
   * @param newScl new width/height/radius
   */
  void scale(float[] newScl);
}
