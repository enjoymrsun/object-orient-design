import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test public methods for the Point class.
 */
public class PointTest {
  private Point p1;
  private Point p2;

  /**
   * Initialize two point object used to test.
   */
  @Before
  public void setUp() {
    p1 = new Point(2.453, -7.675);
    p2 = new Point(-32.124, 51.157);
  }

  /**
   * Test the getX() method.
   */
  @Test
  public void testGetX() {
    assertEquals(2.453, p1.getX(), 0.00001);
    assertEquals(-32.124, p2.getX(), 0.00001);
  }

  /**
   * Test the getY() method.
   */
  @Test
  public void testGetY() {
    assertEquals(-7.675, p1.getY(), 0.00001);
    assertEquals(51.157, p2.getY(), 0.00001);
  }

  /**
   * Test the toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("2.45,-7.68; ", p1.toString());
    assertEquals("-32.12,51.16; ", p2.toString());
  }

}