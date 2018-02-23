import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test public methods in PolynomialImpl object.
 */
public class PolynomialTest {
  private Polynomial p1;
  private Polynomial p2;
  private Polynomial p3;
  private Polynomial p4;
  private Polynomial p5;
  private Polynomial p6;
  private Polynomial p7;
  private Polynomial p8;
  private Polynomial p9;
  private Polynomial p10;

  /**
   * Create 10 Polynomial objects used to test.
   */
  @Before
  public void setUp() {
    p1 = new PolynomialImpl("4x^3 +3x^1");
    p2 = new PolynomialImpl("5x^1");
    // no parameter
    p3 = new PolynomialImpl();
    // constant
    p4 = new PolynomialImpl("-5");
    // empty String
    p5 = new PolynomialImpl("");
    // term not in order
    p6 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
    // more than one term has same power
    p7 = new PolynomialImpl("-3x^4 -2x^4 -5 +11x^1");
    p8 = new PolynomialImpl("3x^5 -6x^3 -2x^1 +10");
    p9 = new PolynomialImpl("2x^15 -3x^5 +6x^3 +4x^1 -8");
    p10 = new PolynomialImpl();
  }

  /**
   * Test whether the IllegalArgumentException is thrown when the given string does not follow the
   * format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPolynomial1() {
    Polynomial p100 = new PolynomialImpl("Hello!");
  }

  /**
   * Test whether the IllegalArgumentException is thrown when the given string does not follow the
   * format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPolynomial2() {
    Polynomial p100 = new PolynomialImpl("4x^3+5x^6");
  }

  /**
   * Test whether the IllegalArgumentException is thrown when the given string does not follow the
   * format.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalPolynomial3() {
    Polynomial p100 = new PolynomialImpl("4x^-3+5x^0");
  }

  /**
   * Test whether addTerm method works correctly.
   */
  @Test
  public void testAddTerm() {
    // power already exists
    p1.addTerm(2, 1);
    assertEquals("4x^3+5x^1", p1.toString());
    p2.addTerm(4, 3);
    assertEquals("4x^3+5x^1", p2.toString());
    p3.addTerm(-3, 0);
    // add a number
    assertEquals("-3", p3.toString());
    p6.addTerm(4, 3);
    assertEquals("-2x^5-3x^4+4x^3+11x^1-5", p6.toString());
    p10.addTerm(-4, 3);
    assertEquals("-4x^3", p10.toString());
    p10.addTerm(3, 0);
    assertEquals("-4x^3+3", p10.toString());
  }

  /**
   * Test whether the IllegalArgumentException is thrown when giving power is less than 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalAddTerm() {
    p1.addTerm(-2, -2);
  }

  /**
   * Test whether removeTerm method works correctly.
   */
  @Test
  public void testRemoveTerm() {
    p1.removeTerm(1);
    assertEquals("4x^3", p1.toString());
    p3.removeTerm(1);
    assertEquals("0", p3.toString());
    // remove number
    p4.removeTerm(0);
    assertEquals("0", p4.toString());
    p5.removeTerm(1);
    assertEquals("0", p5.toString());
    p6.removeTerm(5);
    assertEquals("-3x^4+11x^1-5", p6.toString());
    // remove a polynomial with the given initialize input string has duplicate power for this power
    p7.removeTerm(4);
    assertEquals("11x^1-5", p7.toString());
    p8.removeTerm(5);
    assertEquals("-6x^3-2x^1+10", p8.toString());
    p9.removeTerm(0);
    assertEquals("2x^15-3x^5+6x^3+4x^1", p9.toString());
    assertEquals("0", p10.toString());
    p9.removeTerm(5);
    assertEquals("2x^15+6x^3+4x^1", p9.toString());
  }

  /**
   * Test whether getDegree method works correctly.
   */
  @Test
  public void testGetDegree() {
    assertEquals(3, p1.getDegree());
    // Empty String Polynomial
    assertEquals(0, p5.getDegree());
    assertEquals(5, p6.getDegree());
    assertEquals(15, p9.getDegree());
    p9.removeTerm(15);
    assertEquals(5, p9.getDegree());
    assertEquals(0, p10.getDegree());
    // Singe number Polynomial
    assertEquals(0, p4.getDegree());
  }

  /**
   * Test whether getCoefficient method works correctly.
   */
  @Test
  public void testGetCoefficient() {
    assertEquals(4, p1.getCoefficient(3));
    assertEquals(0, p1.getCoefficient(2));
    assertEquals(0, p3.getCoefficient(3));
    // find number
    assertEquals(-5, p6.getCoefficient(0));
    assertEquals(11, p7.getCoefficient(1));
    assertEquals(10, p8.getCoefficient(0));
    assertEquals(-3, p9.getCoefficient(5));
    assertEquals(0, p10.getCoefficient(2));
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
    assertEquals(-5, p4.evaluate(3), 0.001);
    assertEquals(1, p6.evaluate(1), 0.001);
    assertEquals(125.4883, p8.evaluate(2.3), 0.001);
    assertEquals(-887.2565, p9.evaluate(-1.5), 0.001);
    assertEquals(0, p10.evaluate(-3), 0.001);
  }

  /**
   * Test whether add method works correctly.
   */
  @Test
  public void testAdd() {
    Polynomial goodPoly1 = new PolynomialImpl("2x^1");
    Polynomial goodPoly2 = new PolynomialImpl("-3");

    assertEquals("4x^3+5x^1", p1.add(goodPoly1).toString());
    assertEquals("2x^1", p3.add(goodPoly1).toString());
    assertEquals("-2x^5-3x^4+13x^1-5", p6.add(goodPoly1).toString());
    assertEquals("2x^15+2x^1+2", p8.add(p9).toString());
    // add a number Polynomial
    assertEquals("2x^15+2x^1-1", p9.add(goodPoly2).add(p8).toString());
  }

  /**
   * Test whether multiply method works correctly.
   */
  @Test
  public void testMultiply() {
    Polynomial goodPoly1 = new PolynomialImpl("2x^2");
    Polynomial goodPoly2 = new PolynomialImpl("2x^2 +1x^1");
    Polynomial goodPoly3 = new PolynomialImpl("3");

    assertEquals("8x^5+4x^4+6x^3+3x^2", p1.multiply(goodPoly2).toString());
    assertEquals("0", p3.multiply(goodPoly1).toString());
    Polynomial p11 = new PolynomialImpl("3x^4 -5x^3 +2x^1 -4");
    Polynomial p12 = new PolynomialImpl("2x^3 +2x^2 +4");
    assertEquals("6x^7-4x^6-10x^5+16x^4-24x^3-8x^2+8x^1-16", p11.multiply(p12).toString());
    assertEquals("-6x^5-9x^4+33x^1-15", goodPoly3.multiply(p6).toString());
  }

  /**
   * Test whether isSame method works correctly.
   */
  @Test
  public void testIsSame() {
    Polynomial testPoly1 = new PolynomialImpl("-3x^4 -2x^4 +11x^1 -5");
    Polynomial testPoly2 = new PolynomialImpl("-3x^4 -2x^4 +11x^1");
    Polynomial testPoly3 = new PolynomialImpl("-5");

    assertTrue(p7.isSame(testPoly1));
    assertFalse(p7.isSame(testPoly2));
    assertFalse(p8.isSame(p9));
    // Number Polynomial Comparison
    assertTrue(testPoly3.isSame(p4));
  }

  /**
   * Test whether toString method works correctly.
   */
  @Test
  public void testToString() {
    assertEquals("4x^3+3x^1", p1.toString());
    assertEquals("5x^1", p2.toString());
    // no Argument
    assertEquals("0", p3.toString());
    // Number Argument
    assertEquals("-5", p4.toString());
    // Empty String Argument
    assertEquals("0", p5.toString());
    assertEquals("-2x^5-3x^4+11x^1-5", p6.toString());
    // need to put same power term together
    assertEquals("-5x^4+11x^1-5", p7.toString());
  }
}