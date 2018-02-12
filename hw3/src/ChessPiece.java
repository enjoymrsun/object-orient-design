/**
 * This interface contains all operations that all types of chess piece should support.
 */
public interface ChessPiece {

  /**
   * Return the current row of this Chess Piece.
   *
   * @return current row of this Chess Piece
   */
  int getRow();

  /**
   * Return the current col of this Chess Piece.
   *
   * @return current col of this Chess Piece
   */
  int getColumn();

  /**
   * Return the color of this Chess Piece.
   *
   * @return color of this Chess Piece
   */
  Color getColor();

  /**
   * Return whether the position is a valid on board coordinate.
   *
   * @param row row need to be checked
   * @param col col need to be checked
   * @return whether the target coordinate is on board or not
   */
  boolean isOnBoard(int row, int col);

  /**
   * Return whether the current Chess Piece can move to the specified position or not.
   *
   * @param row the target row
   * @param col the target col
   * @return the possibility of the move action
   */
  boolean canMove(int row, int col);

  /**
   * Return whether the current Chess Piece can kill the specified Chess Piece.
   *
   * @param piece the target Chess Piece that need to be killed
   * @return the possibility of the kill action
   */
  boolean canKill(ChessPiece piece);

}
