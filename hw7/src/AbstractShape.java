/**
 * This is an abstract class that implements Interface Shape.
 */
public abstract class AbstractShape implements Shape {

  protected char name;
  protected double[] pos;
  protected double[] scl;
  protected double[] col;
  protected int t1;
  protected int t2;

  /**
   * Construct a new AbstractShape Object.
   *
   * @param name name
   * @param pos  bottom left corner or center coordinates
   * @param scl  width/height or radius
   * @param col  color
   * @param t1   the time when the AbstractShape appears
   * @param t2   the time when the AbstractShape disappears
   */
  public AbstractShape(char name, double[] pos, double[] scl, double[] col, int t1, int t2) {
    this.name = name;
    this.pos = pos;
    this.scl = scl;
    this.col = col;
    this.t1 = t1;
    this.t2 = t2;
  }

  @Override
  public char getName() {
    return this.name;
  }

  @Override
  public double[] getPos() {
    return this.pos;
  }

  @Override
  public double[] getScl() {
    return this.scl;
  }

  @Override
  public double[] getCol() {
    return this.col;
  }

  @Override
  public int getT1() {
    return this.t1;
  }

  @Override
  public int getT2() {
    return this.t2;
  }

  @Override
  public void changeColor(double[] newCol) {
    this.col = newCol;
  }

  @Override
  public void move(double[] newPos) {
    this.pos = newPos;
  }

  @Override
  public void scale(double[] newScl) {
    this.scl = newScl;
  }
}
