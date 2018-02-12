/**
 * This class represents a Knight. It offers all the operations mandated by the ChessPiece
 * interface.
 */
public class Knight extends AbstractChessPiece {
  /**
   * Create a Knight object with the specified coordinate and color.
   *
   * @param row initial row of the Knight
   * @param col initial col of the Knight
   * @param c   initial Color of this Knight
   */
  public Knight(int row, int col, Color c) {
    super(row, col, c);
  }

  /**
   * Return whether the current Knight can move to the specified position or not.
   *
   * @param row the target row
   * @param col the target col
   * @return the possibility of the move action
   */
  @Override
  public boolean canMove(int row, int col) {
    if (isOnBoard(row, col)) {
      int deltaX = Math.abs(row - this.getRow());
      int deltaY = Math.abs(col - this.getColumn());
      boolean samePos = this.getRow() == row && this.getColumn() == col;
      return deltaX == 1 && deltaY == 2 || deltaX == 2 && deltaY == 1 || samePos;
    }
    return false;
  }

  /**
   * Return whether the current Knight can kill the specified Chess Piece.
   *
   * @param piece the target Chess Piece that need to be killed
   * @return the possibility of the kill action
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    if (this.getColor() == piece.getColor()) {
      return false;
    }
    return canMove(piece.getRow(), piece.getColumn());
  }
}
