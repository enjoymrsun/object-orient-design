import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * This class is the model to represent an animation.
 */
public class Animation {
  private List<Shape> shapes;
  private List<Operation> oprs;

  /**
   * Initialize an Animation object.
   */
  public Animation() {
    this.shapes = new ArrayList<>();
    this.oprs = new ArrayList<>();
  }

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
  public void addShape(ShapeType type, char name, double x, double y, double w, double h, double r,
                       double g, double b, int t1, int t2) throws IllegalArgumentException {
    Shape s;
    if (findShape(name) != null) {
      throw new IllegalArgumentException("The name already exists.");
    }

    if (type.equals(ShapeType.RECTANGLE)) {
      s = new Rectangle(name, new double[]{x, y}, new double[]{w, h},
              new double[]{r, g, b}, t1, t2);
      this.shapes.add(s);
    }
    else if (type.equals(ShapeType.OVAL)) {
      s = new Oval(name, new double[]{x, y}, new double[]{w, h}, new double[]{r, g, b}, t1, t2);
      this.shapes.add(s);
    }
  }

  /**
   * Find the shape in the list by name.
   *
   * @param name the name of the shape
   * @return the shape
   */
  public Shape findShape(char name) {
    for (Shape s : this.shapes) {
      if (s.getName() == name) {
        return s;
      }
    }
    return null;
  }

  /**
   * Check if the shape has overlapping operations.
   *
   * @param name the name of the shape
   * @param type the type of the operation
   * @param t1   the operation start time
   * @param t2   the operation end time
   * @throws IllegalArgumentException if the operation time overlaps with an existing one
   */
  public void checkOverlap(char name, OperationType type, int t1, int t2)
          throws IllegalArgumentException {
    for (Operation o : this.oprs) {
      if (o.getName() == name && o.getType() == type && !(t1 >= o.getT2() || t2 <= o.getT1())) {
        throw new IllegalArgumentException("Operation time cannot overlap.");
      }
    }
  }

  /**
   * Move a shape to a new position.
   *
   * @param name the name of the shape
   * @param x    X coordinate of the new bottom left corner or center
   * @param y    Y coordinate of the new bottom left corner or center
   * @param t1   start time of the moving operation
   * @param t2   end time of the moving operation
   * @throws IllegalArgumentException if the time is invalid, or the shape does not exist
   */
  public void moveShape(char name, double x, double y, int t1, int t2)
          throws IllegalArgumentException {

    Shape s = findShape(name);
    if (s == null) {
      throw new IllegalArgumentException("The shape does not exist.");
    }

    if (t2 <= t1 || s.getT1() > t1 || s.getT2() < t2) {
      throw new IllegalArgumentException("The time is invalid.");
    }

    // Check if the operation time overlaps with an existing one
    checkOverlap(name, OperationType.MOVE, t1, t2);

    Operation o = new Operation(s.getName(), OperationType.MOVE, s.getPos(), new double[]{x, y},
            t1, t2);
    s.move(new double[]{x, y});
    this.oprs.add(o);
  }

  /**
   * Scale a shape with new width and height.
   *
   * @param name the name of the shape
   * @param w    new width or X radius
   * @param h    new height or Y radius
   * @param t1   start time of the scaling operation
   * @param t2   end time of the scaling operation
   * @throws IllegalArgumentException if the time is invalid, or the shape does not exist
   */
  public void scaleShape(char name, double w, double h, int t1, int t2)
          throws IllegalArgumentException {

    Shape s = findShape(name);
    if (s == null) {
      throw new IllegalArgumentException("The shape does not exist.");
    }

    if (t2 <= t1 || s.getT1() > t1 || s.getT2() < t2) {
      throw new IllegalArgumentException("The time is invalid.");
    }

    // Check if the operation time overlaps with an existing one
    checkOverlap(name, OperationType.SCALE, t1, t2);

    Operation o = new Operation(s.getName(), OperationType.SCALE, s.getScl(), new double[]{w, h},
            t1, t2);
    s.scale(new double[]{w, h});
    this.oprs.add(o);
  }

  /**
   * Change the shape with a new color.
   *
   * @param name the name of the shape
   * @param r    r value of the new color's RGB
   * @param g    g value of the new color's RGB
   * @param b    b value of the new color's RGB
   * @param t1   start time of the changing color operation
   * @param t2   end time of the changing color operation
   * @throws IllegalArgumentException if the time is invalid, or the shape does not exist
   */
  public void changeShapeColor(char name, double r, double g, double b, int t1, int t2)
          throws IllegalArgumentException {

    Shape s = findShape(name);
    if (s == null) {
      throw new IllegalArgumentException("The shape does not exist.");
    }

    if (t2 <= t1 || s.getT1() > t1 || s.getT2() < t2) {
      throw new IllegalArgumentException("The time is invalid.");
    }

    // Check if the operation time overlaps with an existing one
    checkOverlap(name, OperationType.CHANGECOLOR, t1, t2);

    Operation o = new Operation(s.getName(), OperationType.CHANGECOLOR,
            s.getCol(), new double[]{r, g, b}, t1, t2);
    s.changeColor(new double[]{r, g, b});
    this.oprs.add(o);
  }

  /**
   * Implement the text output rendering.
   */
  public void textRender() {

    // Sort the lists first.
    this.shapes.sort(Comparator.comparing(Shape::getT1));
    this.oprs.sort(Comparator.comparing(Operation::getT1));

    // Print shapes.
    if (this.shapes.size() != 0) {
      System.out.println("Shapes");
    }
    for (Shape s : this.shapes) {
      System.out.println("Name:" + s.getName());
      System.out.println("Type:" + s.getType());
      if (s.getType().equals(ShapeType.RECTANGLE)) {
        System.out.println("Min corner: (" + s.getPos()[0] + "," + s.getPos()[1]
                + "), Width: " + s.getScl()[0] + ", Height: " + s.getScl()[1]
                + ", Color: (" + s.getCol()[0] + "," + s.getCol()[1] + "," + s.getCol()[2] + ")");
      }
      else if (s.getType().equals(ShapeType.OVAL)) {
        System.out.println("Center: (" + s.getPos()[0] + "," + s.getPos()[1]
                + "), X radius: " + s.getScl()[0] + ", Y radius: " + s.getScl()[1]
                + ", Color: (" + s.getCol()[0] + "," + s.getCol()[1] + "," + s.getCol()[2] + ")");
      }
      System.out.println("Appears at t=" + s.getT1());
      System.out.println("Disappears at t=" + s.getT2());
      System.out.println("Disappears at t=" + s.getT2());
      System.out.println();
    }

    // Print operations.
    for (Operation o : this.oprs) {
      if (o.getType().equals(OperationType.MOVE)) {
        System.out.println("Shape " + o.getName() + " moves from (" + o.getStart()[0] + ","
                + o.getStart()[1] + ") to (" + o.getEnd()[0] + "," + o.getEnd()[1]
                + ") from t=" + o.getT1() + " to t=" + o.getT2());
      }
      else if (o.getType().equals(OperationType.SCALE)) {
        System.out.println("Shape " + o.getName() + " scales from Width: " + o.getStart()[0]
                + ", Height: " + o.getStart()[1] + " to Width: " + o.getEnd()[0] + ", Height: "
                + o.getEnd()[1] + " from t=" + o.getT1() + " to t=" + o.getT2());
      }
      else if (o.getType().equals(OperationType.CHANGECOLOR)) {
        System.out.println("Shape " + o.getName() + " changes color from ("
                + o.getStart()[0] + "," + o.getStart()[1] + "," + o.getStart()[2]
                + ") to (" + o.getEnd()[0] + "," + o.getEnd()[1] + "," + o.getEnd()[2]
                + ") from t=" + o.getT1() + " to t=" + o.getT2());
      }
    }
  }
}
