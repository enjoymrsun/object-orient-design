/**
 * This class represents a Rook. It offers all the operations mandated by the ChessPiece interface.
 */
public class Rook extends AbstractChessPiece {
  /**
   * Create a Rook object with the specified coordinate and color.
   *
   * @param row initial row of the Rook
   * @param col initial col of the Rook
   * @param c   initial Color of this Rook
   */
  public Rook(int row, int col, Color c) {
    super(row, col, c);
  }

  /**
   * Return whether the current Rook can move to the specified position or not.
   *
   * @param row the target row
   * @param col the target col
   * @return the possibility of the move action
   */
  @Override
  public boolean canMove(int row, int col) {
    if (isOnBoard(row, col)) {
      return this.getRow() == row || this.getColumn() == col;
    }
    return false;
  }

  /**
   * Return whether the current Rook can kill the specified Chess Piece.
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
