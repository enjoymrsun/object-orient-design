import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class implements all the methods in Polynomial interface.
 */
public class PolynomialImpl implements Polynomial {
  private List<Term> terms;

  /**
   * Initialize an empty Polynomial Object.
   */
  public PolynomialImpl() {
    this.terms = new ArrayList<>();
  }

  /**
   * Initialize a Polynomial object with the given input string.
   *
   * @param str the given input string need to be parsed
   * @throws NumberFormatException    when the term's power format cannot parsed to integer
   * @throws IllegalArgumentException when the term's power format is invalid
   */
  public PolynomialImpl(String str) throws IllegalArgumentException, NumberFormatException {
    this.terms = new ArrayList<>();

    if (str.length() != 0) {
      String[] strs = str.split(" ");
      for (String s : strs) {
        if (!s.equals("0")) {
          terms.add(new Term(s));
        }
      }

      Map<Integer, Integer> map = new HashMap<>();
      for (Term t : terms) {
        map.put(t.getPower(), map.getOrDefault(t.getPower(), 0) + t.getCoeff());
      }

      terms.clear();
      for (Integer power : map.keySet()) {
        if (map.get(power) != 0) {
          addTerm(map.get(power), power);
        }
      }
    }
  }

  /**
   * Add the term to the Polynomial object with the given coefficient and power.
   *
   * @param coefficient the coefficient need to be created
   * @param power       the power need to be created
   * @throws IllegalArgumentException when the given power is below 0
   */
  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power should be non negative integer.");
    }

    for (Term t : terms) {
      if (t.getPower() == power) {
        terms.remove(t);
        terms.add(new Term(t.getCoeff() + coefficient, power));
        return;
      }
    }
    terms.add(new Term(coefficient, power));
  }

  /**
   * Remove the term in this Polynomial for the specific power.
   *
   * @param power the power needs to be removed
   */
  @Override
  public void removeTerm(int power) {
    for (int i = 0; i < terms.size(); i++) {
      if (terms.get(i).getPower() == power) {
        terms.remove(i);
        break;
      }
    }
  }

  /**
   * Return the highest power among all terms in the Polynomial object.
   *
   * @return highest power among all terms in the Polynomial object, zero terms return 0
   */
  @Override
  public int getDegree() {
    int maxD = 0;
    for (int i = 0; i < terms.size(); i++) {
      maxD = Math.max(maxD, terms.get(i).getPower());
    }

    return maxD;
  }

  /**
   * Return the coefficient of the specific power in a Polynomial object.
   *
   * @param power the power that wants to find
   * @return the coefficient related with the input power, doesn't find return 0
   */
  @Override
  public int getCoefficient(int power) {
    for (Term t : terms) {
      if (t.getPower() == power) {
        return t.getCoeff();
      }
    }
    return 0;
  }

  /**
   * Return the evaluate result of the Polynomial given the input x value.
   *
   * @param x input value for x
   * @return result
   */
  @Override
  public double evaluate(double x) {
    double res = 0;
    for (Term t : terms) {
      res += t.eval(x);
    }

    return res;
  }

  /**
   * Create and return the result Polynomial object from the addition of this and other Polynomial.
   *
   * @param other the other Polynomial used to do the addition
   * @return the result Polynomial object
   */
  @Override
  public Polynomial add(Polynomial other) {
    Map<Integer, Integer> map = new HashMap<>();

    for (Term t : terms) {
      map.put(t.getPower(), map.getOrDefault(t.getPower(), 0) + t.getCoeff());
    }

    int otherD = other.getDegree();
    for (int i = 0; i <= otherD; i++) {
      int otherC = other.getCoefficient(i);
      map.put(i, map.getOrDefault(i, 0) + otherC);
    }

    return transfer(map);
  }

  /**
   * Return the Polynomial object with the given input map { power: coefficient }.
   *
   * @param map input map { power: coefficient }
   * @return Polynomial object
   */
  private Polynomial transfer(Map<Integer, Integer> map) {
    Polynomial newP = new PolynomialImpl();
    for (Integer power : map.keySet()) {
      if (map.get(power) != 0) {
        newP.addTerm(map.get(power), power);
      }
    }

    return newP;
  }

  /**
   * Create and return the result Polynomial object from the multiply of this and other Polynomial.
   *
   * @param other the other Polynomial used to do the multiply
   * @return the result Polynomial object
   */
  @Override
  public Polynomial multiply(Polynomial other) {
    Map<Integer, Integer> map = new HashMap<>();

    int otherD = other.getDegree();
    for (Term t : terms) {
      for (int i = 0; i <= otherD; i++) {
        int otherC = other.getCoefficient(i);
        if (otherC != 0) {
          int newPower = t.getPower() + i;
          int newCoeff = t.getCoeff() * otherC;
          map.put(newPower, map.getOrDefault(newPower, 0) + newCoeff);
        }
      }
    }

    return transfer(map);
  }

  /**
   * Determine whether two Polynomial objects are same or not.
   *
   * @param other the other Polynomial need to be compared
   * @return result of comparison
   */
  @Override
  public boolean isSame(Polynomial other) {
    return this.toString().equals(other.toString());
  }

  /**
   * Return the string format for this Polynomial object.
   *
   * @return string format for this Polynomial object
   */
  @Override
  public String toString() {
    Collections.sort(terms, (t1, t2) -> (t2.getPower() - t1.getPower()));
    StringBuilder sb = new StringBuilder();
    if (terms.size() == 0) {
      return "0";
    }

    for (Term t : terms) {
      sb.append(t.toString());
    }

    if (sb.charAt(0) == '+') {
      sb.deleteCharAt(0);
    }

    return sb.toString();
  }
}
