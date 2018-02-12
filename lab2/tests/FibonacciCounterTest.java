import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * test class for FibonacciCounterTest.
 */
public class FibonacciCounterTest {

  private FibonacciCounter fc1;
  private FibonacciCounter fc2;
  private FibonacciCounter fc3;
  private FibonacciCounter fc4;

  /**
   * set up FibonacciCounter object used for tests.
   */
  @Before
  public void setUp() {
    fc1 = new FibonacciCounter(5);
    fc2 = new FibonacciCounter(1);
    fc3 = new FibonacciCounter(30);
    fc4 = new FibonacciCounter(50);
  }

  /**
   * test cases for invalid counter input.
   */
  @Test
  public void testInvalidConstructor() {
    try {
      FibonacciCounter fca = new FibonacciCounter(-1);
      fail("Should throw an exception but not!!");
    } catch (IllegalArgumentException e) {
      assertEquals(e.getMessage(), "You type in wrong count input!");
    }
  }

  /**
   * test cases for getCount() method.
   */
  @Test
  public void testGetCount() {
    assertEquals(fc1.getCount(), 5);
    assertEquals(fc2.getCount(), 1);
    assertEquals(fc3.getCount(), 30);
  }

  /**
   * test cases for returnVal() method.
   */
  @Test
  public void testReturnVal() {
    assertEquals(fc1.returnVal(), 5);
    assertEquals(fc2.returnVal(), 1);
    assertEquals(fc3.returnVal(), 832040);
    assertEquals("" + fc4.returnVal(), "12586269025");
    assertEquals((new FibonacciCounter(29)).incre().returnVal(), 832040);
    assertEquals((new FibonacciCounter(31)).decre().returnVal(), 832040);
  }

  /**
   * test cases for incre() method.
   */
  @Test
  public void testIncr() {
    assertEquals(fc1.incre().getCount(), 6);
    assertEquals(fc2.incre().getCount(), 2);
    assertEquals(fc3.incre().getCount(), 31);
  }

  /**
   * test cases for decre() method.
   */
  @Test
  public void testDecr() {
    assertEquals(fc1.decre().getCount(), 4);
    assertEquals(fc2.decre().getCount(), 0);
    assertEquals(fc2.decre().decre().getCount(), 0);
    assertEquals(fc3.decre().getCount(), 29);
  }
}
