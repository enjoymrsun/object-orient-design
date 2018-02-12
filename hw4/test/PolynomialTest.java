import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PolynomialTest {
  @Before
  public void setUp() {
    Polynomial p1 = new PolynomialImpl("3x^5 -6x^3 -2x^1 +10");
    Polynomial p2 = new PolynomialImpl("2x^15 -3x^5 +6x^3 +4x^1 -8");
    Polynomial p3 = new PolynomialImpl();
  }

  @Test
  public void testAddTerm() {
  }

  @Test
  public void testRemoveTerm() {
  }

  @Test
  public void testGetDegree() {
  }

  @Test
  public void testGetCoefficient() {
  }

  @Test
  public void testEvaluate() {
  }

  @Test
  public void testAdd() {
    assertEquals("2x^15");
  }

  @Test
  public void testMultiply() {

  }

  @Test
  public void testIsSame() {
  }

}