public interface Polynomial {

  void addTerm(int coefficient, int power);

  void removeTerm(int power);

  int getDegree();

  int getCoefficient(int power);

  double evaluate(double x);

  Polynomial add(Polynomial other);

  Polynomial multiply(Polynomial other);

  boolean isSame(Polynomial other);
}
