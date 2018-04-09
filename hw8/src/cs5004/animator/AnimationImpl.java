package cs5004.animator;
import cs5004.animator.util.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class is the model to represent an animation.
 */
public final class AnimationImpl implements IAnimation {
  private Map<Shape, List<Operation>> ani = new HashMap<>();

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
  public Map<Shape, List<Operation>> getAni() {
    return this.ani;
  }

  /**
   * Get Operation list of this animation.
   *
   * @return operation list of this animation
   */
  public List<Operation> getOprs() {
    List<List<Operation>> lists = new ArrayList<>(ani.values());
    List<Operation> list = lists.stream().flatMap(Collection::stream).collect(Collectors.toList());
    list.sort(Comparator.comparing(Operation::getT1));
    return list;
  }

  /**
   * Describes constructing any animation, shape-by-shape and step-by-step.
   */
  public static final class Builder implements TweenModelBuilder<IAnimation> {

    //TweenModelBuilder<IAnimation> 和IAnimation 的关系我没太搞懂，
    // 这块constructor以及后面的return type应该有问题
    private Map<Shape, List<Operation>> ani = new HashMap<>();

    public Builder() {
      this.ani = new HashMap<>();
    }

    private Shape findShape(String name) {
      for (Shape s : ani.keySet()) {
        if (s.getName().equals(name)) {
          return s;
        }
      }
      return null;
    }

    private void checkOverlap(Shape s, OperationType type, int t1, int t2)
            throws IllegalArgumentException {
      for (Operation o : ani.get(s)) {
        if (o.getType() == type && !(t1 >= o.getT2() || t2 <= o.getT1())) {
          throw new IllegalArgumentException("Operation time cannot overlap.");
        }
      }
    }

    @Override
    public TweenModelBuilder<IAnimation> addOval(String name, float cx, float cy, float xRadius,
              float yRadius, float red, float green, float blue, int startOfLife, int endOfLife) {
      Shape s = new Oval(name, new float[]{cx, cy}, new float[]{xRadius, yRadius},
              new float[]{red, green, blue}, startOfLife, endOfLife);
      ani.put(s, new ArrayList<>());
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimation> addRectangle(String name, float lx, float ly, float width,
               float height, float red, float green, float blue, int startOfLife, int endOfLife) {
      Shape s = new Rectangle(name, new float[]{lx, ly}, new float[]{width, height},
              new float[]{red, green, blue}, startOfLife, endOfLife);
      ani.put(s, new ArrayList<>());
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimation> addMove(String name, float moveFromX, float moveFromY,
                                      float moveToX, float moveToY, int startTime, int endTime) {

      Shape s = findShape(name);

      checkOverlap(s, OperationType.MOVE, startTime, endTime);

      Operation o = new Operation(name, OperationType.MOVE, new float[]{moveFromX, moveFromY},
              new float[]{moveToX, moveToY}, startTime, endTime);
      s.move(new float[]{moveToX, moveToY});
      ani.get(s).add(o);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimation> addColorChange(String name, float oldR, float oldG,
                      float oldB, float newR, float newG, float newB, int startTime, int endTime) {
      Shape s = findShape(name);

      checkOverlap(s, OperationType.CHANGECOLOR, startTime, endTime);

      Operation o = new Operation(name, OperationType.CHANGECOLOR, new float[]{oldR, oldG, oldB},
              new float[]{newR, newG, newB}, startTime, endTime);
      s.changeColor(new float[]{newR, newG, newB});
      ani.get(s).add(o);
      return this;
    }

    @Override
    public TweenModelBuilder<IAnimation> addScaleToChange(String name, float fromSx, float fromSy,
                                         float toSx, float toSy, int startTime, int endTime) {
      Shape s = findShape(name);

      checkOverlap(s, OperationType.SCALE, startTime, endTime);

      Operation o = new Operation(name, OperationType.SCALE, new float[]{fromSx, fromSy},
              new float[]{toSx, toSy}, startTime, endTime);
      s.scale(new float[]{toSx, toSy});
      ani.get(s).add(o);
      return this;
    }

    @Override
    public IAnimation build() {
      return this;
    }
  }
}
