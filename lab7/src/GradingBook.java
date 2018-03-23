/**
 * Interface class which provides operations on a grade book.
 */
public interface GradingBook {
  /**
   * Add a grade without score to this grading book.
   *
   * @param name       name of the new grade
   * @param totalScore total score of the new grade
   * @param weight     weight of the new grade
   */
  void addGrade(String name, double totalScore, double weight);

  /**
   * Add a grade without score to this grading book.
   *
   * @param name       name of the new grade
   * @param score      score of the new grade
   * @param totalScore total score of the new grade
   * @param weight     weight of the new grade
   */
  void addGrade(String name, double score, double totalScore, double weight);

  /**
   * Assign a grade into its belonged categories.
   *
   * @param gradeName grade name of the grade we want to find
   * @param category  category we want to let grade be in
   */
  void divideGradesIntoCategories(String gradeName, GradeCategory category);

  /**
   * Update the grade with a new score.
   *
   * @param name  the grade we want to find
   * @param score new score
   */
  void updateGrade(String name, double score);

  /**
   * Return the current score for this grading book.
   *
   * @return current score for this grading book
   */
  double getCurrentScore();

  /**
   * Return the current score for the specific category.
   *
   * @param category the category you want to find
   * @return the current score for this category
   */
  double getCurrentScoreWithCategory(GradeCategory category);

  /**
   * Return the percentage of total grade that has been determined.
   *
   * @return the percentage of total grade that has been determined
   */
  double getPercentageGradeDetermined();

  /**
   * Minimum score for this grading book.
   *
   * @return minimum score
   */
  double getMinimumScore();

  /**
   * Maximum score for this grading book.
   *
   * @return maximum score
   */
  double getMaximumScore();
}
