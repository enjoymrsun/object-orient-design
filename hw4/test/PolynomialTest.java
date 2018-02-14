import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class PolynomialTest {
  private Polynomial p1;
  private Polynomial p2;
  private Polynomial p3;

  @Before
  public void setUp() {
    p1 = new PolynomialImpl("3x^5 -6x^3 -2x^1 +10");
    p2 = new PolynomialImpl("2x^15 -3x^5 +6x^3 +4x^1 -8");

    p3 = new PolynomialImpl();
  }

  @Test
  public void testAddTerm() {
    p3.addTerm(-4, 3);
    assertEquals("-4x^3", p3.toString());
    p3.addTerm(3, 0);
    assertEquals("-4x^3+3", p3.toString());
  }

  @Test
  public void testRemoveTerm() {
    p1.removeTerm(5);
    assertEquals("-6x^3-2x^1+10", p1.toString());
    p2.removeTerm(0);
    assertEquals("2x^15-3x^5+6x^3+4x^1", p2.toString());
    assertEquals("0", p3.toString());
    p2.removeTerm(5);
    assertEquals("2x^15+6x^3+4x^1", p2.toString());
  }

  @Test
  public void testGetDegree() {
    assertEquals(15, p2.getDegree());
    p2.removeTerm(15);
    assertEquals(5, p2.getDegree());
    assertEquals(0, p3.getDegree());
  }

  @Test
  public void testGetCoefficient() {
    assertEquals(10, p1.getCoefficient(0));
    assertEquals(-3, p2.getCoefficient(5));
    assertEquals(0, p3.getCoefficient(2));
  }

  @Test
  public void testEvaluate() {
    assertEquals(125.4883, p1.evaluate(2.3), 0.001);
    assertEquals(-887.2565, p2.evaluate(-1.5), 0.001);
    assertEquals(0, p3.evaluate(1), 0.0001);
  }

  @Test
  public void testAdd() {
    assertEquals("2x^15+2x^1+2", p1.add(p2).toString());
  }

  @Test
  public void testMultiply() {
    Polynomial p4 = new PolynomialImpl("3x^4 -5x^3 +2x^1 -4");
    Polynomial p5 = new PolynomialImpl("2x^3 +2x^2 +4");
    assertEquals("6x^7-4x^6-10x^5+16x^4-24x^3-8x^2+8x^1-16", p4.multiply(p5).toString());
  }

  @Test
  public void testIsSame() {
    assertFalse(p1.isSame(p2));
  }

}