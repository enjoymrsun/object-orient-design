package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This class tests whether the operations on shape is correct or not.
 */
public class ShapeTest {
  private Shape rectange;
  private Shape oval;

  /**
   * Prepare 2 kinds of shape instance to test their related operations.
   */
  @Before
  public void setUp() {
    rectange = new Rectangle("rec1", new float[]{4.5f, 7.8f}, new float[]{20.3f, 31.5f},
            new float[]{0.1234f, 0.4355f, 0.8981f}, 410, 3021);
    oval = new Oval("ova1", new float[]{123.123f, 322.124f}, new float[]{17.23f, 12.982f},
            new float[]{0.8913f, 0.5231f, 0.3431f}, 60, 700);
  }

  /**
   * Test the shape's String getName() method.
   */
  @Test
  public void testGetName() {
    assertEquals("rec1", rectange.getName());
    assertEquals("ova1", oval.getName());
  }

  /**
   * Test the shape's ShapeType getType() method.
   */
  @Test
  public void testGetType() {
    assertEquals(ShapeType.RECTANGLE, rectange.getType());
    assertEquals(ShapeType.OVAL, oval.getType());
  }

  /**
   * Test the shape's float[] getPos() method.
   */
  @Test
  public void testGetPos() {
    assertArrayEquals(new float[]{4.5f, 7.8f}, rectange.getPos(), 0.0001f);
    assertArrayEquals(new float[]{123.123f, 322.124f}, oval.getPos(), 0.0001f);
  }

  /**
   * Test the shape's float[] getScl() method.
   */
  @Test
  public void testGetScl() {
    assertArrayEquals(new float[]{20.3f, 31.5f}, rectange.getScl(), 0.0001f);
    assertArrayEquals(new float[]{17.23f, 12.982f}, oval.getScl(), 0.0001f);
  }

  /**
   * Test the shape's float[] getCol() method.
   */
  @Test
  public void testGetCol() {
    assertArrayEquals(new float[]{0.1234f, 0.4355f, 0.8981f}, rectange.getCol(), 0.0001f);
    assertArrayEquals(new float[]{0.8913f, 0.5231f, 0.3431f}, oval.getCol(), 0.0001f);
  }

  /**
   * Test the shape's int getT1() method.
   */
  @Test
  public void testGetT1() {
    assertEquals(410, rectange.getT1());
    assertEquals(60, oval.getT1());
  }

  /**
   * Test the shape's int getT2() method.
   */
  @Test
  public void testGetT2() {
    assertEquals(3021, rectange.getT2());
    assertEquals(700, oval.getT2());
  }

  /**
   * Test the shape's void changeColor(float[] newCol) method.
   */
  @Test
  public void testChangeColor() {
    float[] newColsForR = new float[]{0.1232f, 0.2341f, 0.6574f};
    float[] newColsForO = new float[]{0.7252f, 0.8521f, 0.4251f};
    rectange.changeColor(newColsForR);
    oval.changeColor(newColsForO);
    assertArrayEquals(new float[]{0.1232f, 0.2341f, 0.6574f}, rectange.getCol(), 0.0001f);
    assertArrayEquals(new float[]{0.7252f, 0.8521f, 0.4251f}, oval.getCol(), 0.0001f);
  }

  /**
   * Test the shape's void move(float[] newPos) method.
   */
  @Test
  public void testMove() {
    float[] newPosForR = new float[]{151.141f, 124.11f};
    float[] newPosForO = new float[]{62.12f, 67.15f};
    rectange.move(newPosForR);
    oval.move(newPosForO);
    assertArrayEquals(new float[]{151.141f, 124.11f}, rectange.getPos(), 0.0001f);
    assertArrayEquals(new float[]{62.12f, 67.15f}, oval.getPos(), 0.0001f);
  }

  /**
   * Test the shape's void scale(float[] newScl) method.
   */
  @Test
  public void testScale() {
    float[] newSclForR = new float[]{21.12f, 62.73f};
    float[] newSclForO = new float[]{85.7141f, 145.225f};
    rectange.scale(newSclForR);
    oval.scale(newSclForO);
    assertArrayEquals(new float[]{21.12f, 62.73f}, rectange.getScl(), 0.0001f);
    assertArrayEquals(new float[]{85.7141f, 145.225f}, oval.getScl(), 0.0001f);
  }

}