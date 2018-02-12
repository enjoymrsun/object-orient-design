/**
 * This class represents a Likert question. It offers all the operations mandated by the Question
 * interface.
 */
public class Likert extends QuestionAbstract {
  private LikertQuestionEnum answer;

  /**
   * Initialized a likert question object using the given question, required status and answer.
   *
   * @param question the question text of this Likert question object
   * @param required the required or not option of this Likert question object
   * @param answer   the answer for this Likert question object
   */
  public Likert(String question, boolean required, LikertQuestionEnum answer) {
    super(question, required);
    this.answer = answer;
  }

  /**
   * Create and return a Question object with valid question, required, answer field.
   *
   * @param ans one answer for this question
   * @return a Likert question object with valid answer
   * @throws IllegalArgumentException if answer does not belong to the LikertQuestionEnum category
   */
  public Question answer(String ans) throws IllegalArgumentException {
    Question q;
    switch (ans) {
      case "Strongly Agree":
        q = new Likert(this.question, this.required, LikertQuestionEnum.STRONGLYAGREE);
        break;
      case "Agree":
        q = new Likert(this.question, this.required, LikertQuestionEnum.AGREE);
        break;
      case "Neither Agree nor Disagree":
        q = new Likert(this.question, this.required, LikertQuestionEnum.NEITHERAGREENORDISAGREE);
        break;
      case "Disagree":
        q = new Likert(this.question, this.required, LikertQuestionEnum.DISAGREE);
        break;
      case "Strongly Disagree":
        q = new Likert(this.question, this.required, LikertQuestionEnum.STRONGLYDISAGREE);
        break;
      default:
        throw new IllegalArgumentException("Wrong answer type!");
    }

    return q;
  }

  /**
   * Returns the string representation of this likert type Question object.
   *
   * @return the string representation of this likert type Question object
   */
  @Override
  public String toString() {
    return "Likert{answer='" + answer + "\', question='"
            + question + '\'' + ", required=" + required +
            '}';
  }
}

