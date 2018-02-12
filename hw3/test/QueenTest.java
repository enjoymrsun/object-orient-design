import org.junit.Before;
import org.junit.Test;

import java.rmi.UnexpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class contains all the unit tests for the Queen Chess Piece.
 */
public class QueenTest {
  private Queen q1;
  private Queen q2;
  private Queen q3;

  /**
   * Constructor creates instances of class Queen.
   */
  @Before
  public void setUp() {
    q1 = new Queen(4, 4, Color.WHITE);
    q2 = new Queen(5, 2, Color.BLACK);
    q3 = new Queen(7, 1, Color.WHITE);
  }

  /**
   * Tests if method correctly throws Exception with wrong input coordinate.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    Queen q4 = new Queen(0, 8, Color.WHITE);
  }

  /**
   * Tests if method correctly returns the can move possibility.
   */
  @Test
  public void testCanMove() {
    assertTrue(q1.canMove(5, 4));
    assertTrue(q2.canMove(5, 6));
    assertTrue(q3.canMove(5, 3));
    assertFalse(q1.canMove(2, 3));
    assertFalse(q3.canMove(5, 2));
    assertTrue(q2.canMove(5, 2));
  }

  /**
   * Tests if method correctly returns the can kill possibility.
   */
  @Test
  public void testCanKill() {
    Bishop b1 = new Bishop(5, 5, Color.WHITE);
    Rook r1 = new Rook(3, 3, Color.BLACK);
    assertFalse(q1.canKill(q2));
    assertFalse(q1.canKill(q3));
    assertFalse(q1.canKill(b1));
    assertTrue(q2.canKill(b1));
    assertFalse(q3.canKill(r1));
    assertTrue(q2.canKill(new Pawn(3, 2, Color.WHITE)));
    assertTrue(q1.canKill(new Rook(6, 6, Color.BLACK)));
  }

  /**
   * Test the consistency for the canKill and canMove methods.
   */
  @Test
  public void testConsistency1() {
    Queen cp1 = new Queen(2, 2, Color.WHITE);
    Queen cp2 = new Queen(6, 2, Color.BLACK);

    assertTrue(cp1.canMove(cp2.getRow(), cp2.getColumn()));
    try {
      if (!cp1.canKill(cp2)) {
        throw new UnexpectedException("canMove and canKill are not in consistency.");
      }
    } catch (UnexpectedException e) {
      fail("Two methods: canMove and canKill and not in consistency.");
    }
  }

  /**
   * Test the consistency for the canKill and canMove methods.
   */
  @Test
  public void testConsistency2() {
    Queen cp1 = new Queen(2, 2, Color.WHITE);
    Queen cp2 = new Queen(5, 3, Color.BLACK);

    assertFalse(cp1.canMove(cp2.getRow(), cp2.getColumn()));
    try {
      if (cp1.canKill(cp2)) {
        throw new UnexpectedException("canMove and canKill are not in consistency.");
      }
    } catch (UnexpectedException e) {
      fail("Two methods: canMove and canKill and not in consistency.");
    }
  }
}