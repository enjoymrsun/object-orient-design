import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test public methods in Term class.
 */
public class TermTest {
  private Term t1;
  private Term t2;
  private Term t3;

  /**
   * Initialize 3 term objects used to test.
   */
  @Before
  public void setUp() {
    t1 = new Term("+1x^1");
    t2 = new Term("4");
    t3 = new Term("-3x^2");
  }

  /**
   * Test with invalid input power.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    Term t7 = new Term(3, -2);
  }

  /**
   * Test with invalid input string format.
   */
  @Test(expected = NumberFormatException.class)
  public void testConstructor2() {
    Term t7 = new Term("n");
  }

  /**
   * Test with invalid power format for the input string.
   */
  @Test(expected = NumberFormatException.class)
  public void testConstructor3() {
    Term t7 = new Term("3x^");
  }

  /**
   * Test with invalid power format for the input string.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    Term t7 = new Term("-3x^-4");
  }

  /**
   * Test with invalid power format for the input string.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    Term t7 = new Term("3x^0");
  }

  /**
   * Test the getCoeff method.
   */
  @Test
  public void testGetCoeff() {
    assertEquals(1, t1.getCoeff());
    assertEquals(4, t2.getCoeff());
    assertEquals(-3, t3.getCoeff());
  }

  /**
   * Test the getPower method.
   */
  @Test
  public void testGetPower() {
    assertEquals(1, t1.getPower());
    assertEquals(0, t2.getPower());
    assertEquals(2, t3.getPower());
  }

  /**
   * Test the eval(double x) method.
   */
  @Test
  public void testEval() {
    assertEquals(2.0, t1.eval(2), 0.0001);
    assertEquals(4.0, t2.eval(3), 0.0001);
    assertEquals(-14.52, t3.eval(2.2), 0.0001);
  }

  /**
   * Test the toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("+1x^1", t1.toString());
    assertEquals("+4", t2.toString());
    assertEquals("-3x^2", t3.toString());
    assertEquals("-3", new Term("-3").toString());
  }

}