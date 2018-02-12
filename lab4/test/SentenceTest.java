import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests for all methods which the interface Sentence contains.
 */
public class SentenceTest {
  private Sentence s1;
  private Sentence s2;
  private Sentence s3;
  private Sentence s4;
  private Sentence s5;
  private Sentence s6;

  /**
   * Initialize 6 sentence objects to check the methods.
   */
  @Before
  public void setUp() {
    s1 = new WordNode("The", new WordNode("OOD", new WordNode("is",
            new PunctuationNode(",", new WordNode("I", new WordNode("mean",
                    new PunctuationNode(",", new WordNode("awesome",
                            new PunctuationNode("!", new EmptyNode())))))))));
    s2 = new WordNode("haha", new EmptyNode());
    s3 = new WordNode("Good", new WordNode("Work", new WordNode("HAHA",
            new PunctuationNode("!", new EmptyNode()))));
    s4 = new WordNode("Hello",
            new PunctuationNode(",", new WordNode("world",
                    new PunctuationNode("!", new EmptyNode()))));
    s5 = new WordNode("Hello",
            new PunctuationNode(",", new WordNode("world", new EmptyNode())));
    s6 = new EmptyNode();
  }

  /**
   * Test the getNumberOfWords() method.
   */
  @Test
  public void testGetNumberOfWords() {
    assertEquals(0, s6.getNumberOfWords());
    assertEquals(6, s1.getNumberOfWords());
  }

  /**
   * Test the LongestWord() method.
   */
  @Test
  public void testLongestWord() {
    assertEquals(null, s6.longestWord());
    assertEquals("awesome", s1.longestWord());
    Sentence s7 = new PunctuationNode(".", new EmptyNode());
    assertEquals(null, s7.longestWord());
  }

  /**
   * Test the toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("The OOD is, I mean, awesome!", s1.toString());
    assertEquals("haha.", s2.toString());
    assertEquals("", s6.toString());
    assertEquals("Hello, world.", s5.toString());
    assertEquals("Good Work HAHA!", s3.toString());
  }

  /**
   * Test the testMerge() method.
   */
  @Test
  public void testMerge() {
    assertEquals("The OOD is, I mean, awesome! haha.", s1.merge(s2).toString());
    assertEquals("", s6.merge(s6).toString());
    assertEquals("Hello, world Hello, world!", s5.merge(s4).toString());
  }

  /**
   * Test the clone() method.
   */
  @Test
  public void testClone() {
    assertEquals("The OOD is, I mean, awesome!", s1.clone().toString());
    assertEquals("Hello, world Hello, world!", s5.merge(s4).clone().toString());
    assertEquals("Good Work HAHA!", s3.clone().toString());
  }
}