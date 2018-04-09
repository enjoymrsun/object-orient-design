/**
 * This class represents an operation.
 */
public class Operation {
  private char name;
  private OperationType type;
  private double[] start;
  private double[] end;
  private int t1;
  private int t2;

  /**
   * Construct an operation object.
   *
   * @param name  name of the shape
   * @param type  type of the operation
   * @param start the values of pos/scl/col before the operation
   * @param end   the values of pos/scl/col after the operation
   * @param t1    operation start time
   * @param t2    operation end time
   */
  public Operation(char name, OperationType type, double[] start, double[] end, int t1, int t2) {
    this.name = name;
    this.type = type;
    this.start = start;
    this.end = end;
    this.t1 = t1;
    this.t2 = t2;
  }

  /**
   * Get the name of the shape.
   *
   * @return name of the shape
   */
  public char getName() {
    return this.name;
  }

  /**
   * Get the operation type.
   *
   * @return the operation type
   */
  public OperationType getType() {
    return this.type;
  }

  /**
   * Get the values before operation.
   *
   * @return the values before operation
   */
  public double[] getStart() {
    return this.start;
  }

  /**
   * Get the values after operation.
   *
   * @return the values after operation
   */
  public double[] getEnd() {
    return this.end;
  }

  /**
   * Get the operation start time.
   *
   * @return start time
   */
  public int getT1() {
    return this.t1;
  }

  /**
   * Get the operation end time.
   *
   * @return end time
   */
  public int getT2() {
    return this.t2;
  }
}
