/**
 * Abstract class used for Chess Pieces' different types.
 */
public abstract class AbstractChessPiece implements ChessPiece {
  private int row;
  private int col;
  private Color c;

  /**
   * Create an AbstractChessPiece object with the specified coordinate and color.
   *
   * @param row initial row of the Chess Piece
   * @param col initial col of the Chess Piece
   * @param c   initial Color of this Chess Piece
   * @throws IllegalArgumentException invalid input for the initial coordinate
   */
  public AbstractChessPiece(int row, int col, Color c) throws IllegalArgumentException {
    if (!isOnBoard(row, col)) {
      throw new IllegalArgumentException("Invalid Position.");
    }
    this.row = row;
    this.col = col;
    this.c = c;
  }

  /**
   * Return whether the position is a valid on board coordinate.
   *
   * @param row row need to be checked
   * @param col col need to be checked
   * @return whether the target coordinate is on board or not
   */
  @Override
  public boolean isOnBoard(int row, int col) {
    return (row >= 0 && row < 8 && col >= 0 && col < 8);
  }

  /**
   * Return the row of this AbstractChessPiece.
   *
   * @return row of this AbstractChessPiece
   */
  @Override
  public int getRow() {
    return this.row;
  }

  /**
   * Return the col of this AbstractChessPiece.
   *
   * @return col of this AbstractChessPiece
   */
  @Override
  public int getColumn() {
    return this.col;
  }

  /**
   * Return the color of this AbstractChessPiece.
   *
   * @return color of this AbstractChessPiece
   */
  @Override
  public Color getColor() {
    return this.c;
  }
}
