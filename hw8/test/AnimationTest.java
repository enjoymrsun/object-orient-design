import org.junit.Before;
import org.junit.Test;

/**
 * This class contains tests for the animation.
 */
public class AnimationTest {
  Animation ani;

  /**
   * Initialize the animation.
   */
  @Before
  public void setup() {
    ani = new Animation();

    ani.addShape(ShapeType.RECTANGLE, 'R', 200.0, 200.0, 50.0, 100.0,
            1.0, 0.0, 0.0, 1, 100);
    ani.addShape(ShapeType.OVAL, 'C', 500.0, 100.0, 60.0, 30.0,
            0.0, 0.0, 1.0, 6, 100);

    ani.moveShape('R', 300.0, 300.0, 10, 50);
    ani.moveShape('C', 500.0, 400.0, 20, 70);
    ani.changeShapeColor('C', 0.0, 1.0, 0.0, 50, 80);
    ani.scaleShape('R', 25.0, 100.0, 51, 70);
    ani.moveShape('R', 200.0, 200.0, 70, 100);

    ani.textRender();
  }

  /**
   * Test Illegal AddShape when the name already exists.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddShape() {
    ani.addShape(ShapeType.OVAL, 'C', 500.0, 100.0, 60.0, 30.0,
            0.0, 0.0, 1.0, 6, 100);
  }

  /**
   * Test Illegal MoveShape when the time is invalid, t2 is less than t1.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalMoveShape() {
    ani.moveShape('R', 300.0, 300.0, 60, 50);
  }

  /**
   * Test Illegal ScaleShape when the time is invalid, the shape does not appear during that time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalScaleShape() {
    ani.moveShape('C', 300.0, 300.0, 1, 50);
  }

  /**
   * Test Illegal ChangeShapeColor when the shape does not exist.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalChangeShapeColor() {
    ani.moveShape('E', 300.0, 300.0, 10, 50);
  }

  /**
   * Test CheckOverLap if the operation times overlap.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testCheckOverlap() {
    ani.moveShape('R', 200.0, 200.0, 20, 60);
  }
}