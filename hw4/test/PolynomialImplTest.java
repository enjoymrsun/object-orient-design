import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 * A Junit test class for PolynomialImpl class.
 */
public class PolynomialImplTest {
  private PolynomialImpl p1;
  private PolynomialImpl p2;
  private PolynomialImpl p3;
  private PolynomialImpl p4;
  private PolynomialImpl p5;
  private PolynomialImpl p6;
  private PolynomialImpl p7;

  /**
   * Initialize new PolynomialImpl Objects.
   */
  @Before
  public void setUp() {
    p1 = new PolynomialImpl("4x^3 +3x^1");
    p2 = new PolynomialImpl("5x^1");
    p3 = new PolynomialImpl(); // no parameter
    p4 = new PolynomialImpl("-5"); // constant
    p5 = new PolynomialImpl(""); // empty String
    p6 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");// term not in order
    // more than one term has same power
    p7 = new PolynomialImpl("-3x^4 -2x^4 -5 +11x^1");
  }

  /**
   * Test whether PolynomialImpl have been created with the correct parameters or not. It does this
   * by using toString methods.
   */
  @Test
  public void testObjectData() {
    assertEquals("4x^3+3x^1", p1.toString());
    assertEquals("5x^1", p2.toString());
    assertEquals("0", p3.toString());
    assertEquals("-5", p4.toString());
    assertEquals("0", p5.toString());
    assertEquals("-2x^5-3x^4+11x^1-5", p6.toString());
    assertEquals("-5x^4+11x^1-5", p7.toString());
  }

  /**
   * Test whether the IllegalArgumentException is thrown when the given string does not follow the
   * format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPolynomial1() {
    PolynomialImpl badPoly;

    badPoly = new PolynomialImpl("y");
  }

  /**
   * Test whether the IllegalArgumentException is thrown when the given string does not follow the
   * format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPolynomial2() {
    PolynomialImpl badPoly;

    badPoly = new PolynomialImpl("4x^3+5x^6");
  }

  /**
   * Test whether the IllegalArgumentException is thrown when the given string does not follow the
   * format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPolynomial3() {
    PolynomialImpl badPoly;

    badPoly = new PolynomialImpl("Hello!");
  }

  /**
   * Test whether the IllegalArgumentException is thrown when giving power is less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddTerm() {
    p1.addTerm(-2, -2);
  }

  /**
   * Test whether addTerm method works correctly.
   */
  @Test
  public void testAddTerm() {
    p1.addTerm(2,1);
    assertEquals("4x^3+5x^1", p1.toString());
    p2.addTerm(4, 3);
    assertEquals("4x^3+5x^1", p2.toString());
    p3.addTerm(4, 3);
    assertEquals("4x^3", p3.toString());
    p6.addTerm(4,3);
    assertEquals("-2x^5-3x^4+4x^3+11x^1-5", p6.toString());
  }

  /**
   * Test whether removeTerm method works correctly.
   */
  @Test
  public void testRemoveTerm() {
    p1.removeTerm(1);
    assertEquals("4x^3", p1.toString());
    p6.removeTerm(5);
    assertEquals("-3x^4+11x^1-5", p6.toString());
    p3.removeTerm(1);
    assertEquals("0", p3.toString());
    p5.removeTerm(1);
    assertEquals("0", p3.toString());
  }

  /**
   * Test whether getDegree method works correctly.
   */
  @Test
  public void testGetDegree() {
    assertEquals(3, p1.getDegree());
    assertEquals(0, p3.getDegree());
    assertEquals(5, p6.getDegree());
  }

  /**
   * Test whether getCoefficient method works correctly.
   */
  @Test
  public void testGetCoefficient() {
    assertEquals(4, p1.getCoefficient(3));
    assertEquals(0, p1.getCoefficient(2));
    assertEquals(0, p3.getCoefficient(3));
    assertEquals(-5, p6.getCoefficient(0));
  }

  /**
   * Test whether evaluate method works correctly.
   */
  @Test
  public void testEvaluate() {
    assertEquals(0, p1.evaluate(0), 0.001);
    assertEquals(0.304, p1.evaluate(0.1), 0.001);
    assertEquals(38, p1.evaluate(2), 0.001);
    assertEquals(0, p3.evaluate(2), 0.001);
    assertEquals(1, p6.evaluate(1), 0.001);
  }

  /**
   * Test whether add method works correctly.
   */
  @Test
  public void testAdd() {
    Polynomial goodPoly = new PolynomialImpl("2x^1");

    assertEquals("4x^3+5x^1", p1.add(goodPoly).toString());
    assertEquals("2x^1", p3.add(goodPoly).toString());
    assertEquals("-2x^5-3x^4+13x^1-5", p6.add(goodPoly).toString());
  }

  /**
   * Test whether multiply method works correctly.
   */
  @Test
  public void testMultiply() {
    Polynomial goodPoly = new PolynomialImpl("2x^2");
    Polynomial goodPoly2 = new PolynomialImpl("2x^2 +1x^1");

    assertEquals("8x^5+4x^4+6x^3+3x^2", p1.multiply(goodPoly2).toString());
    assertEquals("0", p3.multiply(goodPoly).toString());
  }

  /**
   * Test whether isSame method works correctly.
   */
  @Test
  public void testIsSame() {
    PolynomialImpl testPoly1;
    PolynomialImpl testPoly2;

    testPoly1 = new PolynomialImpl("-3x^4 -2x^4 +11x^1 -5");
    testPoly2 = new PolynomialImpl("-3x^4 -2x^4 +11x^1");

    assertTrue(p7.isSame(testPoly1));
    assertFalse(p7.isSame(testPoly2));
  }

  /**
   * Test whether toString method works correctly.
   */
  @Test
  public void testToString() {
    assertEquals("4x^3+3x^1", p1.toString());
    assertEquals("5x^1", p2.toString());
    assertEquals("0", p3.toString());
    assertEquals("-5", p4.toString());
    assertEquals("0", p5.toString());
    assertEquals("-2x^5-3x^4+11x^1-5", p6.toString());
    assertEquals("-5x^4+11x^1-5", p7.toString());
  }
}