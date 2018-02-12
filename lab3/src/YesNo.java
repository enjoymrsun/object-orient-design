/**
 * This class represents a yes or no question. It offers all the operations mandated by the Question
 * interface.
 */
public class YesNo extends QuestionAbstract {
  private String answer;

  /**
   * Initialized a YesNo object using the given question, required status and answer.
   *
   * @param question the question text of this YesNo question object
   * @param required the required or not option of this YesNo question object
   * @param answer   the answer for this YesNo question object
   */
  public YesNo(String question, boolean required, String answer) {
    super(question, required);
    this.answer = answer;
  }

  /**
   * Create and return a Question object with valid question, required, answer field.
   *
   * @param ans one answer for this question
   * @return a YesNo question object with valid answer
   * @throws IllegalArgumentException if the ans String is not "Yes" and not "No"
   */
  public Question answer(String ans) throws IllegalArgumentException {
    if (ans.equals("Yes") || ans.equals("No")) {
      return new YesNo(this.question, this.required, ans);
    }

    throw new IllegalArgumentException("Wrong answer input.");
  }

  /**
   * Returns the string representation of this yes or no type Question object.
   *
   * @return the string representation of this yes or no type Question object
   */
  @Override
  public String toString() {
    return "YesNo{answer='" + answer + "\', question='"
            + question + '\'' + ", required=" + required +
            '}';
  }
}
