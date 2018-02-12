/**
 * This class represents an essay question. It offers all the operations mandated by the Question
 * interface.
 */
public class Essay extends QuestionAbstract {
  private String answer;

  /**
   * Initialized an essay object using the given question, required status and answer.
   *
   * @param question the question text of this Essay question object
   * @param required the required or not option of this Essay question object
   * @param answer   the answer for this Essay question object
   */
  public Essay(String question, boolean required, String answer) {
    super(question, required);
    this.answer = answer;
  }

  /**
   * Create and return a Question object with valid question, required, answer field.
   *
   * @param ans one answer for this question
   * @return an Essay question object with valid answer
   * @throws IllegalArgumentException if length of the answer is longer than 140 characters.
   */
  public Question answer(String ans) throws IllegalArgumentException {
    if (ans.length() > 140) {
      throw new IllegalArgumentException("Answer should be no more than 140 characters.");
    }

    return new Essay(this.question, this.required, ans);
  }

  /**
   * Returns the string representation of this essay type Question object.
   *
   * @return the string representation of this essay type Question object
   */
  @Override
  public String toString() {
    return "Essay{answer='" + answer + "\', question='" + question + '\''
            + ", required=" + required +
            '}';
  }
}
