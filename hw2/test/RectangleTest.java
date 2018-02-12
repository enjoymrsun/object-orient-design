import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests for various Rectangles overlap intersect and union with
 * each other.
 */
public class RectangleTest {
  private Rectangle r1;
  private Rectangle r2;
  private Rectangle r3;
  private Rectangle r4;
  private Rectangle r5;
  private Rectangle r6;
  private Rectangle r7;
  private Rectangle r8;
  private Rectangle r9;
  private Rectangle r10;
  private Rectangle r11;

  /**
   * Initializes 11 rectangles used to test.
   */
  @Before
  public void setup() {
    r1 = new Rectangle(0, 0, 4, 4);
    r2 = new Rectangle(4, 0, 4, 4);
    r3 = new Rectangle(0, 4, 4, 4);
    r4 = new Rectangle(4, 4, 4, 4);
    r5 = new Rectangle(2, 2, 4, 4);
    r6 = new Rectangle(10, 8, 4, 4);
    r7 = new Rectangle(0, 0, 8, 8);
    r8 = new Rectangle(-5, -5, 4, 4);
    r9 = new Rectangle(2, 10, 4, 6);
    r10 = new Rectangle(0, 12, 8, 2);
    r11 = new Rectangle(11, 10, 2, 4);
  }

  /**
   * Test with invalid Rectangle width input argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    Rectangle r12 = new Rectangle(0, 0, -2, 2);
  }

  /**
   * Test with invalid Rectangle height input argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    Rectangle r12 = new Rectangle(0, 0, 2, -2);
  }

  /**
   * Test with invalid Rectangle width and height input argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    Rectangle r12 = new Rectangle(0, 0, -2, -2);
  }

  /**
   * Test whether two Rectangle objects overlap or not.
   */
  @Test
  public void testOverlap() {
    assertEquals(true, r1.overlap(r2));
    assertEquals(true, r2.overlap(r1));
    assertEquals(true, r1.overlap(r4));
    assertEquals(true, r4.overlap(r1));
    assertEquals(true, r1.overlap(r3));
    assertEquals(true, r3.overlap(r1));
    assertEquals(true, r5.overlap(r1));
    assertEquals(true, r5.overlap(r2));
    assertEquals(true, r5.overlap(r3));
    assertEquals(true, r5.overlap(r4));
    assertEquals(false, r6.overlap(r1));
    assertEquals(false, r6.overlap(r2));
    assertEquals(false, r6.overlap(r3));
    assertEquals(false, r6.overlap(r4));
    assertEquals(false, r6.overlap(r5));
    assertEquals(true, r1.overlap(r7));
    assertEquals(true, r7.overlap(r4));
    assertEquals(true, r5.overlap(r7));
    assertEquals(true, r7.overlap(r5));
    assertEquals(true, r1.overlap(r1));
    assertEquals(false, r8.overlap(r7));
    assertEquals(false, r5.overlap(r8));
    assertEquals(true, r9.overlap(r10));
    assertEquals(true, r10.overlap(r9));
    assertEquals(true, r6.overlap(r11));
  }

  /**
   * Test the correctness of the intersection of two Rectangles.
   */
  @Test
  public void testIntersect() {
    assertEquals((new Rectangle(4, 0, 0, 4)).toString(), r1.intersect(r2).toString());
    assertEquals((new Rectangle(0, 4, 4, 0)).toString(), r3.intersect(r1).toString());
    assertEquals((new Rectangle(2, 2, 2, 2)).toString(), r1.intersect(r5).toString());
    assertEquals((new Rectangle(2, 4, 2, 2)).toString(), r3.intersect(r5).toString());
    assertEquals((new Rectangle(0, 0, 4, 4)).toString(), r1.intersect(r7).toString());
    assertEquals((new Rectangle(0, 4, 4, 4)).toString(), r7.intersect(r3).toString());
    assertEquals((new Rectangle(4, 4, 4, 4)).toString(), r7.intersect(r4).toString());
    assertEquals((new Rectangle(2, 2, 4, 4)).toString(), r7.intersect(r5).toString());
    assertEquals((new Rectangle(2, 2, 4, 4)).toString(), r5.intersect(r7).toString());
    assertEquals((new Rectangle(4, 4, 0, 0)).toString(), r1.intersect(r4).toString());
    assertEquals((new Rectangle(2, 12, 4, 2)).toString(), r9.intersect(r10).toString());
    assertEquals((new Rectangle(11, 10, 2, 2)).toString(), r11.intersect(r6).toString());
  }

  /**
   * Test with Rectangles which are not intersected each other.
   */
  @Test(expected = NoSuchElementException.class)
  public void testIntersect1() {
    r1.intersect(r6);
  }

  /**
   * Test with Rectangles which are not intersected each other.
   */
  @Test(expected = NoSuchElementException.class)
  public void testIntersect2() {
    r4.intersect(r6);
  }

  /**
   * Test with Rectangles which are not intersected each other.
   */
  @Test(expected = NoSuchElementException.class)
  public void testIntersect3() {
    r5.intersect(r6);
  }

  /**
   * Test with Rectangles which are not intersected each other.
   */
  @Test(expected = NoSuchElementException.class)
  public void testIntersect4() {
    r6.intersect(r8);
  }

  /**
   * Test the correctness of the union of two Rectangle objects.
   */
  @Test
  public void testUnion() {
    assertEquals((new Rectangle(0, 0, 6, 6)).toString(), r1.union(r5).toString());
    assertEquals((new Rectangle(0, 0, 8, 8)).toString(), r1.union(r4).toString());
    assertEquals((new Rectangle(0, 2, 6, 6)).toString(), r3.union(r5).toString());
    assertEquals((new Rectangle(4, 4, 10, 8)).toString(), r4.union(r6).toString());
    assertEquals((new Rectangle(0, 0, 8, 8)).toString(), r7.union(r1).toString());
    assertEquals((new Rectangle(0, 0, 14, 12)).toString(), r7.union(r6).toString());
    assertEquals((new Rectangle(0, 0, 8, 8)).toString(), r5.union(r7).toString());
    assertEquals((new Rectangle(0, 0, 8, 8)).toString(), r7.union(r5).toString());
    assertEquals((new Rectangle(-5, -5, 11, 11)).toString(), r5.union(r8).toString());
    assertEquals((new Rectangle(-5, -5, 19, 17)).toString(), r8.union(r6).toString());
    assertEquals((new Rectangle(0, 10, 8, 6)).toString(), r10.union(r9).toString());
    assertEquals((new Rectangle(10, 8, 4, 6)).toString(), r11.union(r6).toString());
  }

  /**
   * Test the correctness of the string representation of the rectangle object.
   */
  @Test
  public void testToString() {
    assertEquals("x:0, y:0, w:4, h:4", r1.toString());
    assertEquals("x:2, y:2, w:4, h:4", r5.toString());
    assertEquals("x:10, y:8, w:4, h:4", r6.toString());
    assertEquals("x:2, y:2, w:12, h:10", r5.union(r6).toString());
    assertEquals("x:2, y:2, w:4, h:4", r7.intersect(r5).toString());
    assertEquals("x:-5, y:-5, w:9, h:9", r5.intersect(r1).union(r8).toString());
    assertEquals("x:4, y:0, w:0, h:4", r1.intersect(r2).toString());
    assertEquals("x:4, y:4, w:4, h:0", r4.intersect(r2).toString());
    assertEquals("x:4, y:4, w:0, h:0", r1.intersect(r4).toString());
  }
}