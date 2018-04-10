package cs5004.animator.model;

/**
 * This is an abstract class that implements Interface Shape.
 */
public abstract class AbstractShape implements Shape {

  protected String name;
  protected float[] pos;
  protected float[] scl;
  protected float[] col;
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
  public AbstractShape(String name, float[] pos, float[] scl, float[] col, int t1, int t2) {
    this.name = name;
    this.pos = pos;
    this.scl = scl;
    this.col = col;
    this.t1 = t1;
    this.t2 = t2;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public float[] getPos() {
    return this.pos;
  }

  @Override
  public float[] getScl() {
    return this.scl;
  }

  @Override
  public float[] getCol() {
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
  public void changeColor(float[] newCol) {
    this.col = newCol;
  }

  @Override
  public void move(float[] newPos) {
    this.pos = newPos;
  }

  @Override
  public void scale(float[] newScl) {
    this.scl = newScl;
  }
}
