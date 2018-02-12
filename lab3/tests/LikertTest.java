import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains all the unit tests for the question type: Likert.
 */
public class LikertTest {
  private Likert l1;
  private Likert l2;

  /**
   * Initializes 2 Likert question objects used to test.
   */
  @Before
  public void setUp() {
    l1 = new Likert("Are you a man?", true, LikertQuestionEnum.STRONGLYAGREE);
    l2 = new Likert("Are you a girl?", false, LikertQuestionEnum.DISAGREE);
  }

  /**
   * Test the getQuestionText method.
   */
  @Test
  public void testGetQuestionText() {
    assertEquals("Are you a man?", l1.getQuestionText());
    assertEquals("Are you a girl?", l2.getQuestionText());
  }

  /**
   * Test the getStatus method.
   */
  @Test
  public void testGetStatus() {
    assertTrue(l1.getStatus());
    assertFalse(l2.getStatus());
  }

  /**
   * Test the answer method in Likert class.
   */
  @Test
  public void testAnswer1() {
    Likert l3 = new Likert("Are you a man?", true, LikertQuestionEnum.STRONGLYDISAGREE);
    assertEquals(l3.toString(), l1.answer("Strongly Disagree").toString());
  }

  /**
   * Test the answer method in Likert class with expected exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswe2() {
    l1.answer("haha");
  }


}