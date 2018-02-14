public class Term {
  private int coeff;
  private int power;

  public Term(int coeff, int power) throws IllegalArgumentException {
    this.coeff = coeff;
    this.power = power;

//    if (this.coeff == 0) {
//      throw new IllegalArgumentException("Coefficient of Term should not be 0.");
//    }
  }

  public Term(String s) throws IllegalArgumentException {
    int idx = s.indexOf("x^");
    if (idx < 0) {
      this.coeff = Integer.parseInt(s);
      this.power = 0;
    } else {
      String s1 = s.substring(0, idx);
      String s2 = s.substring(idx + 2);
      this.coeff = s1.equals("") ? 1 : Integer.parseInt(s1);
      this.power = s2.equals("") ? 0 : Integer.parseInt(s2);

      if (this.power <= 0) {
        throw new IllegalArgumentException("Power of Term should be positive integer.");
      }
    }

//    if (this.coeff == 0) {
//      throw new IllegalArgumentException("Coefficient of Term should not be 0.");
//    }
  }

  public int getCoeff() {
    return coeff;
  }

  public int getPower() {
    return power;
  }

  public double eval(double x) {
    return this.coeff * Math.pow(x, power);
  }

  @Override
  public String toString() {
    if (power == 0) {
      return coeff > 0 ? "+" + coeff : "" + coeff;
    } else {
      return coeff > 0 ? "+" + coeff + "x^" + power : coeff + "x^" + power;
    }
  }
}
