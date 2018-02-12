import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This class contains all the unit tests for the Pawn Chess Piece.
 */
public class PawnTest {
  private Pawn p1;
  private Pawn p2;
  private Pawn p3;

  /**
   * Constructor creates instances of class Pawn.
   */
  @Before
  public void setUp() {
    p1 = new Pawn(2, 4, Color.WHITE);
    p2 = new Pawn(5, 5, Color.BLACK);
    p3 = new Pawn(6, 7, Color.BLACK);
  }

  /**
   * Tests if method correctly throws Exception with wrong input coordinate.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    Pawn p4 = new Pawn(-2, 0, Color.WHITE);
  }

  /**
   * Tests if method correctly throws Exception with wrong input coordinate.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    Pawn p4 = new Pawn(1, 10, Color.WHITE);
  }

  /**
   * Tests if method correctly throws Exception with wrong input coordinate.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    Pawn p4 = new Pawn(9, -2, Color.WHITE);
  }

  /**
   * Tests if method correctly returns the can move possibility.
   */
  @Test
  public void testCanMove() {
    assertFalse(p1.canMove(3, 5));
    assertTrue(p1.canMove(3, 4));
    assertTrue(p2.canMove(4, 5));
    assertFalse(p2.canMove(4, 6));
    assertFalse(p3.canMove(6, 8));
    assertFalse(p3.canMove(4, 7));
    assertFalse(p1.canMove(4, 4));
    assertTrue(p2.canMove(5, 5));
  }

  /**
   * Tests if method correctly returns the can kill possibility.
   */
  @Test
  public void testCanKill() {
    Queen q1 = new Queen(3, 5, Color.BLACK);
    Knight k1 = new Knight(4, 6, Color.WHITE);
    Rook r1 = new Rook(5, 7, Color.WHITE);
    assertTrue(p1.canKill(q1));
    assertTrue(p2.canKill(k1));
    assertFalse(p3.canKill(r1));
    assertFalse(p3.canKill(new Rook(5, 6, Color.BLACK)));
    assertFalse(p1.canKill(new Queen(3, 3, Color.WHITE)));
  }
}