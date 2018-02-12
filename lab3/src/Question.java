/**
 * This interface contains all operations that all types of question should support.
 */
public interface Question {

  /**
   * Return the question text of this Question object.
   *
   * @return the question text
   */
  String getQuestionText();

  /**
   * Return the status of this question, either required or optional.
   *
   * @return required or optional of this question
   */
  boolean getStatus();

  /**
   * Create and return the question of same kind with the answer specified.
   *
   * @param ans answer for this question
   * @return new Question object
   */
  Question answer(String ans);

}