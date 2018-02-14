/**
 * This interface contains all operations that all types of Polynomial should support.
 */
public interface Polynomial {

  /**
   *
   * @param coefficient
   * @param power
   */
  void addTerm(int coefficient, int power);

  /**
   * Remove the
   *
   * @param power
   */
  void removeTerm(int power);

  /**
   *
   *
   * @return
   */
  int getDegree();

  /**
   * Return the coefficient of the specific power in a Polynomial object.
   *
   * @param power the power that wants to find
   * @return the coefficient needs to be checked
   */
  int getCoefficient(int power);

  /**
   * Return the evaluate result of the Polynomial given the input x value.
   *
   * @param x input value for x
   * @return evaluate result
   */
  double evaluate(double x);

  /**
   * Create and return the result Polynomial object from the addition of this and other Polynomial.
   *
   * @param other the other Polynomial used to do the addition
   * @return the result Polynomial object
   */
  Polynomial add(Polynomial other);

  /**
   * Create and return the result Polynomial object from the multiply of this and other Polynomial.
   *
   * @param other the other Polynomial used to do the multiply
   * @return the result Polynomial object
   */
  Polynomial multiply(Polynomial other);

  /**
   * Determine whether two Polynomial objects are same or not.
   *
   * @param other the other Polynomial need to be compared
   * @return result of comparison
   */
  boolean isSame(Polynomial other);
}
