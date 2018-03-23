import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test class for Grading Book.
 */
public class GradingBookTest {
  private GradingBook gb1;

  /**
   * Initialize two Grading Book objects to test.
   */
  @Before
  public void setUp() throws Exception {
    gb1 = new GradingBookImpl();
  }

  /**
   * Test the void addGrade(String name, double totalScore, double weight) and the void
   * addGrade(String name, double score, double totalScore, double weight) methods.
   */
  @Test
  public void addGrade() {
    gb1.addGrade("hw1", 20, 30, 3);
    assertEquals(2, gb1.getMinimumScore(), 0.00001);
  }

  /**
   * Test the illegal weight by adding grade.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addGradeIllegal() {
    gb1.addGrade("project3", 30, 101);
  }

  /**
   * Test the divideGradesIntoCategories() method.
   */
  @Test
  public void divideGradesIntoCategories() {
    gb1.addGrade("hw1", 20, 30, 3);
    gb1.divideGradesIntoCategories("hw1", GradeCategory.ASSIGNMENT);
    assertEquals(66.66, gb1.getCurrentScoreWithCategory(GradeCategory.ASSIGNMENT), 0.00001);
  }

  /**
   * Test the updateGrade() method.
   */
  @Test
  public void updateGrade() {
    gb1.addGrade("hw1", 20, 30, 3);
    gb1.updateGrade("hw1", 30);
    assertEquals(3, gb1.getMinimumScore(), 0.00001);
    gb1.addGrade("hw2", 50, 5);
    gb1.updateGrade("hw2", 40);
    assertEquals(7, gb1.getMinimumScore(), 0.00001);
  }

  /**
   * Test the getCurrentScore() method.
   */
  @Test
  public void getCurrentScore() {
    gb1.addGrade("hw1", 20, 50, 5);
    gb1.updateGrade("hw1", 30);
    gb1.addGrade("hw2", 50, 5);
    gb1.updateGrade("hw2", 40);
    assertEquals(70.0, gb1.getCurrentScore(), 0.00001);
  }

  /**
   * Test the getCurrentScoreWithCategory() method.
   */
  @Test
  public void getCurrentScoreWithCategory() {
    gb1.addGrade("hw1", 20, 50, 5);
    gb1.updateGrade("hw1", 30);
    gb1.addGrade("hw2", 50, 5);
    gb1.updateGrade("hw2", 10);
    gb1.divideGradesIntoCategories("hw1", GradeCategory.ASSIGNMENT);
    gb1.divideGradesIntoCategories("hw2", GradeCategory.ASSIGNMENT);
    assertEquals(40.0, gb1.getCurrentScoreWithCategory(GradeCategory.ASSIGNMENT), 0.00001);
    assertEquals(0.0, gb1.getCurrentScoreWithCategory(GradeCategory.EXAM), 0.00001);
  }

  /**
   * Test the getPercentageGradeDetermined() method.
   */
  @Test
  public void getPercentageGradeDetermined() {
    gb1.addGrade("hw1", 20, 50, 5);
    gb1.updateGrade("hw1", 30);
    gb1.addGrade("hw2", 50, 5);
    gb1.updateGrade("hw2", 10);
    assertEquals(0.1, gb1.getPercentageGradeDetermined(), 0.00001);
  }

  /**
   * Test the getMinimumScore() method.
   */
  @Test
  public void getMinimumScore() {
    gb1.addGrade("hw1", 20, 50, 5);
    gb1.addGrade("hw2", 50, 5);
    gb1.updateGrade("hw2", 40);
    assertEquals(6, gb1.getMinimumScore(), 0.00001);
  }

  /**
   * Test the getMaximumScore() method.
   */
  @Test
  public void getMaximumScore() {
    gb1.addGrade("hw1", 20, 50, 5);
    gb1.addGrade("hw2", 50, 5);
    gb1.updateGrade("hw2", 40);
    assertEquals(96, gb1.getMaximumScore(), 0.00001);
  }

}