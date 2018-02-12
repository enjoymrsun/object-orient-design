public class CompoundFraction {
  private int wholePart;
  private int numerator;
  private int denominator;
  private int isPositive;  // 1 means isPositive, -1 means negative

  /**
   * Create and return a CompoundFraction object with the input wholePart, numerator and
   * denominator.
   *
   * @param wholePart   the whole part of this compound fraction
   * @param numerator   the numerator in fractional part of this compound fraction
   * @param denominator the denominator in fractional part of this compound fraction
   */
  public CompoundFraction(int wholePart, int numerator, int denominator) {
    this.wholePart = Math.abs(wholePart);
    this.numerator = Math.abs(numerator);
    this.denominator = Math.abs(denominator);
    this.isPositive = 1;
    if (wholePart < 0 || numerator < 0) {
      isPositive = -1;
    }
    if (denominator <= 0 || wholePart != 0 && numerator < 0) {
      throw new IllegalArgumentException("Wrong Input Type");
    }
  }

  /**
   * Create and return the String representation of this CompoundFraction object.
   *
   * @return the String representation of this CompoundFraction object
   */
  @Override
  public String toString() {
    if (isPositive == -1) {
      return "- " + wholePart + " " + numerator + "/" + denominator;
    }
    return wholePart + " " + numerator + "/" + denominator;
  }

  /**
   * Create and return a new CompoundFraction object composed of this CompoundFraction and the other
   * CompoundFraction's addition.
   *
   * @param other the other CompoundFraction used to calculate the addition
   * @return the sum represented by CompoundFraction object
   */
  public CompoundFraction add(CompoundFraction other) {
    long n1 = (wholePart * denominator + numerator) * isPositive * other.denominator;
    long n2 = (other.wholePart * other.denominator + other.numerator)
            * other.isPositive * denominator;
    long newN = n1 + n2;
    long newD = denominator * other.denominator;
    long gcd = gcd(Math.abs(newN), newD);
    newN /= gcd;
    newD /= gcd;
    int w = (int) (newN / newD);
    int n = (w == 0) ? (int) (newN % newD) : (int) (Math.abs(newN) % newD);
    return new CompoundFraction(w, n, (int) newD);
  }

  private long gcd(long n1, long n2) {
    if (n2 == 0) {
      return n1;
    }
    return gcd(n2, n1 % n2);
  }

  /**
   * Return the comparison result between this CompoundFraction object and the other
   * CompoundFraction object.
   *
   * @param other the other CompoundFraction used to do comparison
   * @return comparison result
   */
  public int compareTo(CompoundFraction other) {
    long n1 = (wholePart * denominator + numerator) * isPositive * other.denominator;
    long n2 = (other.wholePart * other.denominator + other.numerator)
            * other.isPositive * denominator;
    if (n1 == n2) {
      return 0;
    } else if (n1 < n2) {
      return -1;
    } else {
      return 1;
    }
  }
}
