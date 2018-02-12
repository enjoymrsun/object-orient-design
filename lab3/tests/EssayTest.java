import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test class for the Essay class.
 */
public class EssayTest {
  private Essay essay1;
  private Essay essay2;

  /**
   * Constructor creates instances of class Essay.
   */
  @Before
  public void setUp() {
    essay1 = new Essay("What is the best stock you ever bought?", true, "NVDA");
    essay2 = new Essay("Write something?", true, "Hello there!");
  }

  /**
   * Tests if method correctly creates a new Essay given an answer.
   */
  @Test
  public void testAnswer() {
    Essay essay3 = new Essay("What is the best stock you ever bought?", true, "AAPL");
    assertEquals(essay3.toString(), essay1.answer("AAPL").toString());
  }

  /**
   * Tests if method correctly returns the question text of the question.
   */
  @Test
  public void testGetQuestionText() {
    assertEquals("What is the best stock you ever bought?", essay1.getQuestionText());
  }

  /**
   * Tests if method correctly returns the status of the question.
   */
  @Test
  public void testGetStatus() {
    assertTrue(essay1.getStatus());
  }

  /**
   * Tests if method correctly throws Exception if number of characters is greater than 140.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testAnswerException() {
    essay2.answer("HelloHelloHelloHelloHelloHelloHelloHzvvzvzvzsdvsdvelloHelloHel" +
            "loHelloHelloHelloHelloHelloHello" +
            "HelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHelloHe" +
            "dgfasgsadgsdgsadgasdgasgagsfgbssdsshabsfhbdafhadhadnnjnnnnanhaethbn" +
            "lloHelloHelloHello");
  }


}