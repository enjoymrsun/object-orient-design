public class FibonacciCounter {
  private int count;

  /**
   * Given a count number, initialize a FibonacciCounter Object.
   *
   * @param count the count number for Fibonacci Series
   */
  public FibonacciCounter(int count) {
    if (count < 0) {
      throw new IllegalArgumentException("You type in wrong count input!");
    }

    this.count = count;
  }

  /**
   * Return a new FibonacciCounter Object with one bigger count.
   *
   * @return a new FibonacciCounter Object with one bigger count
   */
  public FibonacciCounter incre() {
    return new FibonacciCounter(count + 1);
  }

  /**
   * Return a new FibonacciCounter Object with one lesser count. If count cannot be decremented, it
   * returns the object with the same count.
   *
   * @return a new FibonacciCounter Object with one lesser count
   */
  public FibonacciCounter decre() {
    if (count <= 0) {
      return new FibonacciCounter(0);
    }
    return new FibonacciCounter(count - 1);
  }

  /**
   * Return this FibonacciCounter Object's count.
   *
   * @return the count this Object has
   */
  public int getCount() {
    return count;
  }

  /**
   * Return the Fibonacci number corresponding to the current count.
   *
   * @return the Fibonacci number corresponding to the current count
   */
  public long returnVal() {
    if (this.count < 0) {
      throw new IllegalArgumentException("Illegal Count! Should be equal or greater than 0");
    }

    double sqrt5 = Math.sqrt(5);
    double p1 = Math.pow((1.0 + sqrt5) / 2.0, count);
    double p2 = Math.pow((1.0 - sqrt5) / 2.0, count);

    return (long) ((p1 - p2) / sqrt5);
  }
}
