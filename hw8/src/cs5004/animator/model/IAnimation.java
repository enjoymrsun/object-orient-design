package cs5004.animator.model;

import java.util.List;
import java.util.Map;

/**
 * This interface represents an animation model interface.
 */
public interface IAnimation {

  /**
   * Add a shape to this animation with appear/disappear time.
   *
   * @param type type of the shape
   * @param name name of the shape
   * @param x    X coordinate of the bottom left corner or center
   * @param y    Y coordinate of the bottom left corner or center
   * @param w    width or X radius of the shape
   * @param h    height or Y radius of the shape
   * @param r    r value of the color's RGB
   * @param g    g value of the color's RGB
   * @param b    b value of the color's RGB
   * @param t1   when the shape appears
   * @param t2   when the shape disappears
   * @throws IllegalArgumentException if the name already exists
   */
  void addShape(ShapeType type, String name, float x, float y, float w, float h, float r,
                float g, float b, int t1, int t2) throws IllegalArgumentException;

  /**
   * Move a shape to a new position.
   *
   * @param name the name of the shape
   * @param x0   X coordinate of the old bottom left corner or center
   * @param y0   Y coordinate of the old bottom left corner or center
   * @param x    X coordinate of the new bottom left corner or center
   * @param y    Y coordinate of the new bottom left corner or center
   * @param t1   start time of the moving operation
   * @param t2   end time of the moving operation
   * @throws IllegalArgumentException if the time is invalid, or the shape does not exist
   */
  void moveShape(String name, float x0, float y0, float x, float y, int t1, int t2)
          throws IllegalArgumentException;

  /**
   * Scale a shape with new width and height.
   *
   * @param name the name of the shape
   * @param w0   old width or X radius
   * @param h0   old height or Y radius
   * @param w    new width or X radius
   * @param h    new height or Y radius
   * @param t1   start time of the scaling operation
   * @param t2   end time of the scaling operation
   * @throws IllegalArgumentException if the time is invalid, or the shape does not exist
   */
  void scaleShape(String name, float w0, float h0, float w, float h, int t1, int t2)
          throws IllegalArgumentException;

  /**
   * Change the shape with a new color.
   *
   * @param name the name of the shape
   * @param r0   r value of the old color's RGB
   * @param g0   g value of the old color's RGB
   * @param b0   b value of the old color's RGB
   * @param r    r value of the new color's RGB
   * @param g    g value of the new color's RGB
   * @param b    b value of the new color's RGB
   * @param t1   start time of the changing color operation
   * @param t2   end time of the changing color operation
   * @throws IllegalArgumentException if the time is invalid, or the shape does not exist
   */
  void changeShapeColor(String name, float r0, float g0, float b0, float r, float g, float b,
                        int t1, int t2) throws IllegalArgumentException;

  /**
   * Get the hashmap.
   *
   * @return hashmap of the animation
   */
  Map<Shape, List<Operation>> getAni();
}
