import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test for class Grade.
 */
public class GradeTest {
  private Grade g0;
  private Grade g1;

  /**
   * Initialize grade object.
   */
  @Before
  public void setUp() {
    g0 = new Grade("homework1", 100, 10);
    g1 = new Grade("lab1", 47, 50, 20);
  }

  /**
   * Test getScore().
   */
  @Test
  public void getScore() {
    assertEquals(47, g1.getScore(), 0.00001);
  }

  /**
   * Test getTotalScore().
   */
  @Test
  public void getTotalScore() {
    assertEquals(100, g0.getTotalScore(), 0.00001);
    assertEquals(50, g1.getTotalScore(), 0.00001);
  }

  /**
   * Test getWeight().
   */
  @Test
  public void getWeight() {
    assertEquals(10, g0.getWeight(), 0.00001);
    assertEquals(20, g1.getWeight(), 0.00001);
  }

  /**
   * Test setScore().
   */
  @Test
  public void setScore() {
    g0.setScore(88);
    assertEquals(88, g0.getScore(), 0.00001);
    g1.setScore(33);
    assertEquals(33, g1.getScore(), 0.00001);
  }

  /**
   * Test setTotalScore().
   */
  @Test
  public void setTotalScore() {
    g0.setTotalScore(150);
    assertEquals(150, g0.getTotalScore(), 0.00001);
    g1.setTotalScore(80);
    assertEquals(80, g1.getTotalScore(), 0.00001);
  }

  /**
   * Test setWeight().
   */
  @Test
  public void setWeight() {
    g0.setWeight(5);
    assertEquals(5, g0.getWeight(), 0.00001);
    g1.setWeight(30);
    assertEquals(30, g1.getWeight(), 0.00001);
  }

}