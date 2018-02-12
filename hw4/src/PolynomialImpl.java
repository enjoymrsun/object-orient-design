import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class PolynomialImpl implements Polynomial {
  private List<Term> terms;

  public PolynomialImpl() {
    this.terms = new ArrayList<>();
  }

  public PolynomialImpl(String str) {
    this.terms = new ArrayList<>();
    String[] strs = str.split(" ");
    for (String s : strs) {
      if (!s.equals("0")) {
        terms.add(new Term(s));
      }
    }
  }

  @Override
  public void addTerm(int coefficient, int power) {
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
  public int getCoefficient(int power) throws NoSuchElementException {
    for (Term t : terms) {
      if (t.getPower() == power) {
        return t.getCoeff();
      }
    }
    throw new NoSuchElementException("No matched power term exists.");
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
    // { power: coefficient }
    Map<Integer, Integer> map = new HashMap<>();

    for (Term t : terms) {
      map.put(t.getPower(), map.getOrDefault(t.getPower(), 0) + t.getCoeff());
    }

    int otherD = other.getDegree();
    for (int i = 0; i <= otherD; i++) {
      try {
        int otherC = other.getCoefficient(i);
        map.put(i, map.getOrDefault(i, 0) + otherC);
      } catch (NoSuchElementException e) {
        System.out.println("This power: " + i + " does not exist in the other polynomial.");
      }
    }

    return transfer(map);
  }

  private Polynomial transfer(Map<Integer, Integer> map) {
    Polynomial newP = new PolynomialImpl();
    for (Integer i : map.keySet()) {
      if (map.get(i))
      newP.addTerm(map.get(i), i);
    }

    return newP;
  }

  @Override
  public Polynomial multiply(Polynomial other) {
    // { power: coefficient }
    Map<Integer, Integer> map = new HashMap<>();

    for (Term t : terms) {
      int otherD = other.getDegree();
      for (int i = 0; i <= otherD; i++) {
        try {
          int otherC = other.getCoefficient(i);
          int newPower = t.getPower() + i;
          int newCoeff = t.getCoeff() * otherC;
          map.put(newPower, map.getOrDefault(newPower, 0) + newCoeff);
        } catch (NoSuchElementException e) {
          System.out.println("This power: " + i + " does not exist in the other polynomial.");
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
    for (Term t : terms) {
      sb.append(t.toString());
    }

    if (sb.charAt(0) == '+') {
      sb.deleteCharAt(0);
    }

    return sb.toString();
  }
}
