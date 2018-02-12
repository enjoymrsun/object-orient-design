import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TermTest {
  private Term t1;
  private Term t2;
  private Term t3;

  @Before
  public void setUp() {
    t1 = new Term("+1x^1");
    t2 = new Term("4");
    t3 = new Term("-3x^2");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    Term t7 = new Term(0, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    Term t7 = new Term("0");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    Term t7 = new Term("3x^-4");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    Term t7 = new Term("3x^0");
  }

  @Test
  public void testGetCoeff() {
    assertEquals(1, t1.getCoeff());
    assertEquals(4, t2.getCoeff());
    assertEquals(-3, t3.getCoeff());
  }

  @Test
  public void testGetPower() {
    assertEquals(1, t1.getPower());
    assertEquals(0, t2.getPower());
    assertEquals(2, t3.getPower());
  }

  @Test
  public void testEval() {
    assertEquals(2.0, t1.eval(2), 0.0001);
    assertEquals(4.0, t2.eval(3), 0.0001);
    assertEquals(-14.52, t3.eval(2.2), 0.0001);
  }

  @Test
  public void testToString() {
    assertEquals("+1x^1", t1.toString());
    assertEquals("+4", t2.toString());
    assertEquals("-3x^2", t3.toString());
  }

}