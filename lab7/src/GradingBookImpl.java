import java.util.HashMap;
import java.util.Map;

/**
 * Class that implements methods for GradingBook.
 */
public class GradingBookImpl implements GradingBook {
  private HashMap<String, Grade> grades;
  private HashMap<String, Grade> assignments;
  private HashMap<String, Grade> exams;
  private HashMap<String, Grade> projects;
  private double totalWeight;

  /**
   * Constructor to initialize fields.
   */
  public GradingBookImpl() {
    this.grades = new HashMap<>();
    this.assignments = new HashMap<>();
    this.exams = new HashMap<>();
    this.projects = new HashMap<>();
    this.totalWeight = 0;
  }


  /**
   * Add a grade without score to this grading book.
   *
   * @param name       name of the new grade
   * @param totalScore total score of the new grade
   * @param weight     weight of the new grade
   * @throws IllegalArgumentException when totalWeight exceeds 100
   */
  public void addGrade(String name, double totalScore,
                       double weight) throws IllegalArgumentException {
    if (this.totalWeight + weight > 100) {
      throw new IllegalArgumentException("Your total weights for grade book maximum is 100.");
    }
    if (grades.containsKey(name)) {
      Grade g = grades.get(name);
      g.setTotalScore(totalScore);
      g.setWeight(weight);
    } else {
      Grade newG = new Grade(name, totalScore, weight);
      grades.put(name, newG);
    }
    totalWeight += weight;
  }

  /**
   * Add a grade without score to this grading book.
   *
   * @param name       name of the new grade
   * @param score      score of the new grade
   * @param totalScore total score of the new grade
   * @param weight     weight of the new grade
   */
  public void addGrade(String name, double score, double totalScore, double weight) {
    if (this.totalWeight + weight > 100) {
      throw new IllegalArgumentException("Your total weights for grade book maximum is 100.");
    }
    if (grades.containsKey(name)) {
      Grade g = grades.get(name);
      g.setScore(score);
      g.setTotalScore(totalScore);
      g.setWeight(weight);
    } else {
      Grade newG = new Grade(name, score, totalScore, weight);
      grades.put(name, newG);
    }
    totalWeight += weight;
  }

  /**
   * Assign a grade into its belonged categories.
   *
   * @param gradeName grade name of the grade we want to find
   * @param category  category we want to let grade be in
   */
  public void divideGradesIntoCategories(String gradeName, GradeCategory category) {
    switch (category) {
      case ASSIGNMENT:
        if (grades.containsKey(gradeName)) {
          Grade g = grades.get(gradeName);
          assignments.put(gradeName, g);
        }
        break;

      case EXAM:
        if (grades.containsKey(gradeName)) {
          Grade g = grades.get(gradeName);
          exams.put(gradeName, g);
        }
        break;

      case PROJECT:
        if (grades.containsKey(gradeName)) {
          Grade g = grades.get(gradeName);
          projects.put(gradeName, g);
        }
        break;

      default:
        break;
    }
  }

  /**
   * Update the grade with a new score.
   *
   * @param name  the grade we want to find
   * @param score new score
   */
  public void updateGrade(String name, double score) throws IllegalArgumentException {
    if (grades.containsKey(name)) {
      Grade g = grades.get(name);
      g.setScore(score);
    } else {
      throw new IllegalArgumentException("You cannot update the grade which is not exists");
    }
  }



  /**
   * Return the current score for this grading book.
   *
   * @return current score for this grading book
   */
  @Override
  public double getCurrentScore() {
    double current = 0;
    double totalWeight = 0;
    for (Map.Entry<String, Grade> e : grades.entrySet()) {
      if (e.getValue().getScore() != -1) {
        Grade g = e.getValue();
        current += g.getScore() / g.getTotalScore() * g.getWeight();
      }
      totalWeight += e.getValue().getWeight();
    }
    return ((int) (current / totalWeight * 10000)) / 100.0;
  }

  /**
   * Helper for get current score  for each category.
   *
   * @param map map is the category's grade data
   * @return current score for that category
   */
  private double getCurrentScoreWithCategoryHelper(HashMap<String, Grade> map) {
    if (map.size() == 0) {
      return 0;
    }

    double current = 0;
    double totalWeight = 0;
    for (Map.Entry<String, Grade> e : map.entrySet()) {
      if (e.getValue().getScore() != -1) {
        Grade g = e.getValue();
        current += g.getScore() / g.getTotalScore() * g.getWeight();
      }
      totalWeight += e.getValue().getWeight();
    }
    return ((int) (current / totalWeight * 10000)) / 100.0;
  }

  /**
   * Return the current score for the specific category.
   *
   * @param category the category you want to find
   * @return the current score for this category
   */
  public double getCurrentScoreWithCategory(GradeCategory category) {
    double finalScore = -1;
    switch (category) {
      case ASSIGNMENT:
        finalScore = getCurrentScoreWithCategoryHelper(assignments);
        break;

      case EXAM:
        finalScore = getCurrentScoreWithCategoryHelper(exams);
        break;

      case PROJECT:
        finalScore = getCurrentScoreWithCategoryHelper(projects);
        break;
      default:
        break;
    }

    return finalScore;
  }

  /**
   * Return the percentage of total grade that has been determined.
   *
   * @return the percentage of total grade that has been determined
   */
  @Override
  public double getPercentageGradeDetermined() {
    double totalWeight = 0;
    for (Map.Entry<String, Grade> e : grades.entrySet()) {
      if (e.getValue().getScore() != -1) {
        totalWeight += e.getValue().getWeight();
      }
    }

    return totalWeight / 100.0;
  }

  /**
   * Minimum score for this grading book.
   *
   * @return minimum score
   */
  @Override
  public double getMinimumScore() {
    double current = 0;
    for (Map.Entry<String, Grade> e : grades.entrySet()) {
      if (e.getValue().getScore() != -1) {
        Grade g = e.getValue();
        current += g.getScore() / g.getTotalScore() * g.getWeight();
      }
    }

    return current;
  }

  /**
   * Maximum score for this grading book.
   *
   * @return maximum score
   */
  @Override
  public double getMaximumScore() {
    double minScore = getMinimumScore();

    return minScore + (1 - getPercentageGradeDetermined()) * 100;
  }
}
