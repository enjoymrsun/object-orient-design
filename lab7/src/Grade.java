/**
 * This class represents the grade and its belonged operations.
 */
public class Grade {
  private String name;
  private double score;
  private double totalScore;
  private double weight;

  /**
   * Create a grade without the score it has.
   *
   * @param name       name of this grade
   * @param totalScore total score for this grade
   * @param weight     weight for this grade
   */
  public Grade(String name, double totalScore, double weight) {
    this.name = name;
    this.score = -1;
    this.totalScore = totalScore;
    this.weight = weight;
  }

  /**
   * Create a grade with the score it has.
   *
   * @param name       name of this grade
   * @param score      score for this grade
   * @param totalScore total score for this grade
   * @param weight     weight for this grade
   */
  public Grade(String name, double score, double totalScore, double weight) {
    this.name = name;
    this.score = score;
    this.totalScore = totalScore;
    this.weight = weight;
  }

  /**
   * Return the score for this grade.
   *
   * @return score this grade has
   */
  public double getScore() {
    return score;
  }

  /**
   * Return the total score for this grade.
   *
   * @return total score this grade has
   */

  public double getTotalScore() {
    return totalScore;
  }

  /**
   * Return the weight for this grade.
   *
   * @return weight of this grade
   */
  public double getWeight() {
    return weight;
  }

  /**
   * Set the score for this grade.
   *
   * @param score new score
   */
  public void setScore(double score) {
    this.score = score;
  }

  /**
   * Set the total score for this grade.
   *
   * @param totalScore new total score
   */
  public void setTotalScore(double totalScore) {
    this.totalScore = totalScore;
  }

  /**
   * Set the weight for this grade.
   *
   * @param weight new weight
   */
  public void setWeight(double weight) {
    this.weight = weight;
  }
}
