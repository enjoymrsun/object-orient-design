/**
 * This class represents a Pawn. It offers all the operations mandated by the ChessPiece interface.
 */
public class Pawn extends AbstractChessPiece {
  /**
   * Create a Pawn object with the specified coordinate and color.
   *
   * @param row initial row of the Pawn
   * @param col initial col of the Pawn
   * @param c   initial Color of this Pawn
   */
  public Pawn(int row, int col, Color c) {
    super(row, col, c);
  }

  /**
   * Return whether the current Pawn can move to the specified position or not.
   *
   * @param row the target row
   * @param col the target col
   * @return the possibility of the move action
   */
  @Override
  public boolean canMove(int row, int col) {
    if (isOnBoard(row, col)) {
      boolean samePos = this.getRow() == row && this.getColumn() == col;
      if (getColor() == Color.WHITE) {
        return samePos || row - this.getRow() == 1 && col == this.getColumn();
      } else if (getColor() == Color.BLACK) {
        return samePos || row == this.getRow() - 1 && col == this.getColumn();
      }
    }
    return false;
  }

  /**
   * Return whether the current Pawn can kill the specified Chess Piece.
   *
   * @param piece the target Chess Piece that need to be killed
   * @return the possibility of the kill action
   */
  @Override
  public boolean canKill(ChessPiece piece) {
    if (this.getColor() == piece.getColor()) {
      return false;
    }

    int row = piece.getRow();
    int col = piece.getColumn();
    if (isOnBoard(row, col)) {
      if (getColor() == Color.WHITE) {
        boolean onLeftTopDia = this.getColumn() - 1 == col && this.getRow() + 1 == row;
        boolean onRightTopDia = this.getColumn() + 1 == col && this.getRow() + 1 == row;
        return onLeftTopDia || onRightTopDia;
      } else if (getColor() == Color.BLACK) {
        boolean onLeftDownDia = this.getColumn() - 1 == col && this.getRow() - 1 == row;
        boolean onRightDownDia = this.getColumn() + 1 == col && this.getRow() - 1 == row;
        return onLeftDownDia || onRightDownDia;
      }
    }
    return false;
  }
}
