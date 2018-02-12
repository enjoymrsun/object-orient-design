import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains all the unit tests for the question type: YesNo.
 */
public class YesNoTest {
  private YesNo yn1;
  private YesNo yn2;

  /**
   * Initializes 2 yes no question objects used to test.
   */
  @Before
  public void setUp() {
    yn1 = new YesNo("Are you a man?", true, "Yes");
    yn2 = new YesNo("Are you a girl?", false, "No");
  }

  /**
   * Test the getQuestionText method.
   */
  @Test
  public void testGetQuestionText() {
    assertEquals("Are you a man?", yn1.getQuestionText());
    assertEquals("Are you a girl?", yn2.getQuestionText());
  }

  /**
   * Test the getStatus method.
   */
  @Test
  public void testGetStatus() {
    assertTrue(yn1.getStatus());
    assertFalse(yn2.getStatus());
  }

  /**
   * Test the answer method in YesNo class.
   */
  @Test
  public void testAnswer1() {
    YesNo yn3 = new YesNo("Are you a man?", true, "No");
    YesNo yn4 = new YesNo("Are you a girl?", false, "Yes");
    assertEquals(yn3.toString(), yn1.answer("No").toString());
    assertEquals(yn4.toString(), yn2.answer("Yes").toString());
  }

  /**
   * Test the answer method in YesNo class with expected exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswe2() {
    yn1.answer("haha");
  }
}