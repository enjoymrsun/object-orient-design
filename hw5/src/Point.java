/**
 * This class represent a point and all related operations on a Point object
 */
public class Point {
  private double x;
  private double y;

  /**
   * Initialize a data point with the given x coordinate and y coordinate.
   *
   * @param x x coordinate
   * @param y y coordinate
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Return the x coordinate of the point object.
   *
   * @return x coordinate of the point object
   */
  public double getX() {
    return x;
  }

  /**
   * Return the y coordinate of the point object.
   *
   * @return y coordinate of the point object
   */
  public double getY() {
    return y;
  }

}
