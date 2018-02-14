import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolynomialImpl implements Polynomial {
  private List<Term> terms;

  public PolynomialImpl() {
    this.terms = new ArrayList<>();
  }

  public PolynomialImpl(String str) {
    this.terms = new ArrayList<>();

    if (str.length() != 0) {
      String[] strs = str.split(" ");
      for (String s : strs) {
        if (!s.equals("0")) {
          terms.add(new Term(s));
        }
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

  @Override
  public void addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Power should be positive integer.");
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

  @Override
  public void removeTerm(int power) {
    for (int i = 0; i < terms.size(); i++) {
      if (terms.get(i).getPower() == power) {
        terms.remove(i);
      }
    }
  }

  @Override
  public int getDegree() {
    int maxD = 0;
    for (int i = 0; i < terms.size(); i++) {
      maxD = Math.max(maxD, terms.get(i).getPower());
    }

    return maxD;
  }

  @Override
  public int getCoefficient(int power) {
    for (Term t : terms) {
      if (t.getPower() == power) {
        return t.getCoeff();
      }
    }
    return 0;
  }

  @Override
  public double evaluate(double x) {
    double res = 0;
    for (Term t : terms) {
      res += t.eval(x);
    }

    return res;
  }

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

  private Polynomial transfer(Map<Integer, Integer> map) {
    Polynomial newP = new PolynomialImpl();
    for (Integer i : map.keySet()) {
      if (map.get(i) != 0) {
        newP.addTerm(map.get(i), i);
      }
    }

    return newP;
  }

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

  @Override
  public boolean isSame(Polynomial other) {
    return this.toString().equals(other.toString());
  }

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

  public static void main(String[] args) {
    Polynomial p1 = new PolynomialImpl("-2x^6 +10x^2 -5x^4 +1000");
    Polynomial p2 = new PolynomialImpl("2");
    System.out.println("Code result is: " + p1.multiply(p2).evaluate(3) + ".");
  }
}
