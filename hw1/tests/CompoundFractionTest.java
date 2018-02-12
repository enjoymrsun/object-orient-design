import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class CompoundFractionTest {
  private CompoundFraction cf1;
  private CompoundFraction cf2;
  private CompoundFraction cf3;
  private CompoundFraction cf4;
  private CompoundFraction cf8;

  /**
   * Create 5 CompoundFraction Object used to test.
   */
  @Before
  public void setUp() {
    cf1 = new CompoundFraction(-3, 1, 3);
    cf2 = new CompoundFraction(3, 1, 3);
    cf3 = new CompoundFraction(-2, 1, 2);
    cf4 = new CompoundFraction(3, 1, 4);
    cf8 = new CompoundFraction(3, 1, 4);
  }

  /**
   * Test whether the constructor throws exception when typing in wrong input.
   */
  @Test
  public void testCompoundFraction() {
    CompoundFraction cf5;
    CompoundFraction cf6;
    CompoundFraction cf7;

    try {
      cf5 = new CompoundFraction(1, 2, -3);
      fail("Should throw an exception but not!!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    try {
      cf6 = new CompoundFraction(2, -2, 3);
      fail("Should throw an exception but not!!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    try {
      cf7 = new CompoundFraction(-2, -2, 3);
      fail("Should throw an exception but not!!");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }
  }

  /**
   * Test whether the String representation of the CompoundFraction object is valid or not.
   */
  @Test
  public void testToString() {
    assertEquals("- 3 1/3", cf1.toString());
    assertEquals("3 1/3", cf2.toString());
    assertEquals("- 2 1/2", cf3.toString());
    assertEquals("3 1/4", cf4.toString());
  }

  /**
   * Test whether the addition of two CompoundFraction objects is right or not.
   */
  @Test
  public void testAdd() {
    String s1 = cf1.add(cf2).toString();
    assertEquals(new CompoundFraction(0, 0, 1).toString(), s1);
    String s2 = cf1.add(cf3).toString();
    assertEquals(new CompoundFraction(-5, 5, 6).toString(), s2);
    String s3 = cf2.add(cf4).toString();
    assertEquals(new CompoundFraction(6, 7, 12).toString(), s3);
    String s4 = cf1.add(cf4).toString();
    assertEquals(new CompoundFraction(0, -1, 12).toString(), s4);
    String s5 = cf2.add(cf3).toString();
    assertEquals(new CompoundFraction(0, 5, 6).toString(), s5);
  }

  /**
   * Test whether the method returns the right relationship between these two CompoundFraction.
   */
  @Test
  public void testCompareTo() {
    assertEquals(-1, cf1.compareTo(cf2));
    assertEquals(1, cf2.compareTo(cf3));
    assertEquals(0, cf4.compareTo(cf8));
  }

}