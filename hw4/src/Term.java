/**
 * A class provides methods to deal with the Term.
 */
public class Term {
  private int coeff;
  private int power;

  /**
   * Initialize a Term object with the given coefficient and power.
   *
   * @param coeff the coefficient used to create a Term
   * @param power the power used to create a Term
   * @throws IllegalArgumentException when the given power is less than 0
   */
  public Term(int coeff, int power) throws IllegalArgumentException {
    this.coeff = coeff;
    this.power = power;
    if (power < 0) {
      throw new IllegalArgumentException("Power should be non negative integer.");
    }
  }

  /**
   * Initialize a Term object with the given string.
   *
   * @param s input string
   * @throws IllegalArgumentException when the given string has invalid power format
   */
  public Term(String s) throws IllegalArgumentException {
    int idx = s.indexOf("x^");

    if (idx < 0) {
      try {
        this.coeff = Integer.parseInt(s);
        this.power = 0;
      } catch (Exception e) {
        throw new IllegalArgumentException("String cannot be parsed into an integer.");
      }
    } else {
      try {
        String s1 = s.substring(0, idx);
        String s2 = s.substring(idx + 2);
        this.coeff = s1.equals("") ? 1 : Integer.parseInt(s1);
        this.power = Integer.parseInt(s2);
      } catch (Exception e) {
        throw new IllegalArgumentException("String cannot be parsed into an integer.");
      }

      if (this.power <= 0) {
        throw new IllegalArgumentException("With appearance of x^, power should be positive.");
      }
    }
  }

  /**
   * Return the coefficient.
   *
   * @return the coefficient
   */
  public int getCoeff() {
    return coeff;
  }

  /**
   * Return the power.
   *
   * @return the power
   */
  public int getPower() {
    return power;
  }

  /**
   * Return the evaluation result with the given input x.
   *
   * @param x input x
   * @return evaluation result
   */
  public double eval(double x) {
    return this.coeff * Math.pow(x, power);
  }

  /**
   * Return the string format for the given Term object.
   *
   * @return string format for the given Term object
   */
  @Override
  public String toString() {
    if (power == 0) {
      return coeff > 0 ? "+" + coeff : "" + coeff;
    } else {
      return coeff > 0 ? "+" + coeff + "x^" + power : coeff + "x^" + power;
    }
  }
}
