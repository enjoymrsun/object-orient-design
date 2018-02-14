/**
 * This interface contains all operations that all types of Polynomial should support.
 */
public interface Polynomial {

  /**
   * Add the term to the Polynomial object with the given coefficient and power.
   *
   * @param coefficient the coefficient need to be created
   * @param power the power need to be created
   * @throws IllegalArgumentException when the given power is below 0
   */
  void addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * Remove the term in this Polynomial for the specific power.
   *
   * @param power the power needs to be removed
   */
  void removeTerm(int power);

  /**
   * Return the highest power among all terms in the Polynomial object.
   *
   * @return highest power among all terms in the Polynomial object, zero terms return 0
   */
  int getDegree();

  /**
   * Return the coefficient of the specific power in a Polynomial object.
   *
   * @param power the power that wants to find
   * @return the coefficient related with the input power, doesn't find return 0
   */
  int getCoefficient(int power);

  /**
   * Return the evaluate result of the Polynomial given the input x value.
   *
   * @param x input value for x
   * @return result
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
