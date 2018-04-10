package cs5004.animator.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cs5004.animator.TweenModelBuilder;

/**
 * This class is the model to represent an animation.
 */
public final class AnimationImpl implements IAnimation {

  private Map<Shape, List<Operation>> ani;

  /**
   * Initialize an Animation object.
   */
  public AnimationImpl() {
    this.ani = new HashMap<>();
  }

  /**
   * Get the hashmap.
   *
   * @return hashmap of the animation
   */
  @Override
  public Map<Shape, List<Operation>> getAni() {
    return this.ani;
  }

  /**
   * Find the shape in the list by name.
   *
   * @param name the name of the shape
   * @return the shape
   */
  private Shape findShape(String name) {
    for (Shape s : ani.keySet()) {
      if (s.getName().equals(name)) {
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
  private void checkOverlap(String name, OperationType type, int t1, int t2)
          throws IllegalArgumentException {
    for (Operation o : ani.get(findShape(name))) {
      if (o.getType() == type && !(t1 >= o.getT2() || t2 <= o.getT1())) {
        throw new IllegalArgumentException("Operation time cannot overlap.");
      }
    }
  }

  @Override
  public void addShape(ShapeType type, String name, float x, float y, float w, float h, float r,
                       float g, float b, int t1, int t2) throws IllegalArgumentException {
    Shape s;
    if (findShape(name) != null) {
      throw new IllegalArgumentException("The name already exists.");
    }

    if (type.equals(ShapeType.RECTANGLE)) {
      s = new Rectangle(name, new float[]{x, y}, new float[]{w, h},
              new float[]{r, g, b}, t1, t2);
      ani.put(s, new ArrayList<>());
    } else if (type.equals(ShapeType.OVAL)) {
      s = new Oval(name, new float[]{x, y}, new float[]{w, h}, new float[]{r, g, b}, t1, t2);
      ani.put(s, new ArrayList<>());
    }
  }

  @Override
  public void moveShape(String name, float x0, float y0, float x, float y, int t1, int t2)
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

    Operation o = new Operation(name, OperationType.MOVE, new float[]{x0, y0}, new float[]{x, y},
            t1, t2);
    s.move(new float[]{x, y});
    ani.get(s).add(o);
  }

  @Override
  public void scaleShape(String name, float w0, float h0, float w, float h, int t1, int t2)
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

    Operation o = new Operation(name, OperationType.SCALE, new float[]{w0, h0}, new float[]{w, h},
            t1, t2);
    s.scale(new float[]{w, h});
    ani.get(s).add(o);
  }

  @Override
  public void changeShapeColor(String name, float r0, float g0, float b0, float r, float g, float b,
                               int t1, int t2) throws IllegalArgumentException {

    Shape s = findShape(name);
    if (s == null) {
      throw new IllegalArgumentException("The shape does not exist.");
    }

    if (t2 <= t1 || s.getT1() > t1 || s.getT2() < t2) {
      throw new IllegalArgumentException("The time is invalid.");
    }

    // Check if the operation time overlaps with an existing one
    checkOverlap(name, OperationType.CHANGECOLOR, t1, t2);

    Operation o = new Operation(name, OperationType.CHANGECOLOR, new float[]{r0, g0, b0},
            new float[]{r, g, b}, t1, t2);
    s.changeColor(new float[]{r, g, b});
    ani.get(s).add(o);
  }

  /**
   * Describes constructing any animation, shape-by-shape and step-by-step.
   */
  public static final class Builder implements TweenModelBuilder<IAnimation> {

    private IAnimation model = new AnimationImpl();

    public Builder() {
      this.model = new AnimationImpl();
    }

    @Override
    public TweenModelBuilder<IAnimation> addOval(String name, float cx, float cy, float xRadius,
                                                 float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
      this.model.addShape(ShapeType.OVAL, name, cx, cy, xRadius, yRadius, red, green, blue,
              startOfLife, endOfLife);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimation> addRectangle(String name, float lx, float ly, float width,
                                                      float height, float red, float green, float blue, int startOfLife, int endOfLife) {
      this.model.addShape(ShapeType.RECTANGLE, name, lx, ly, width, height, red, green, blue,
              startOfLife, endOfLife);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimation> addMove(String name, float moveFromX, float moveFromY,
                                                 float moveToX, float moveToY, int startTime, int endTime) {
      this.model.moveShape(name, moveFromX, moveFromY, moveToX, moveToY, startTime, endTime);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimation> addColorChange(String name, float oldR, float oldG,
                                                        float oldB, float newR, float newG, float newB, int startTime, int endTime) {
      this.model.changeShapeColor(name, oldR, oldG, oldB, newR, newG, newB, startTime, endTime);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimation> addScaleToChange(String name, float fromSx, float fromSy,
                                                          float toSx, float toSy, int startTime, int endTime) {
      this.model.scaleShape(name, fromSx, fromSy, toSx, toSy, startTime, endTime);
      return this;
    }

    @Override
    public IAnimation build() {
      return this.model;
    }
  }
}
