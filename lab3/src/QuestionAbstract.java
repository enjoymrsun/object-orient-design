/**
 * Abstract class used for questions' different types.
 */
public abstract class QuestionAbstract implements Question {

  protected String question;
  protected boolean required;

  /**
   * Create a QuestionAbstract object with the specified question and required or not info.
   */
  public QuestionAbstract(String question, boolean required) {
    this.question = question;
    this.required = required;
  }

  @Override
  public String getQuestionText() {
    return this.question;
  }

  @Override
  public boolean getStatus() {
    return this.required;
  }

}
