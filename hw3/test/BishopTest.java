import org.junit.Before;
import org.junit.Test;

import java.rmi.UnexpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class contains all the unit tests for the Bishop Chess Piece.
 */
public class BishopTest {
  private Bishop b1;
  private Bishop b2;
  private Bishop b3;

  /**
   * Constructor creates instances of class Bishop.
   */
  @Before
  public void setUp() {
    b1 = new Bishop(4, 4, Color.BLACK);
    b2 = new Bishop(3, 2, Color.WHITE);
    b3 = new Bishop(5, 6, Color.BLACK);
  }

  /**
   * Tests if method correctly throws Exception with wrong input coordinate.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    Bishop b4 = new Bishop(-2, -2, Color.WHITE);
  }

  /**
   * Tests if method correctly returns the can move possibility.
   */
  @Test
  public void testCanMove() {
    assertTrue(b1.canMove(6, 2));
    assertFalse(b1.canMove(4, 1));
    assertTrue(b2.canMove(2, 3));
    assertFalse(b2.canMove(4, 4));
    assertTrue(b3.canMove(0, 1));
    assertFalse(b3.canMove(8, 10));
    assertTrue(b1.canMove(4, 4));
  }

  /**
   * Tests if method correctly returns the can kill possibility.
   */
  @Test
  public void testCanKill() {
    Knight k1 = new Knight(5, 0, Color.BLACK);
    assertFalse(b1.canKill(b2));
    assertTrue(b2.canKill(k1));
    assertFalse(b2.canKill(b3));
    assertFalse(b3.canKill(new Queen(5, 7, Color.WHITE)));
    assertFalse(b3.canKill(new Pawn(4, 7, Color.BLACK)));
    assertTrue(b2.canKill(new Rook(1, 4, Color.BLACK)));
  }

  /**
   * Test the consistency for the canKill and canMove methods.
   */
  @Test
  public void testConsistency1() {
    Bishop cp1 = new Bishop(2, 2, Color.WHITE);
    Bishop cp2 = new Bishop(6, 6, Color.BLACK);

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
    Bishop cp1 = new Bishop(2, 2, Color.WHITE);
    Bishop cp2 = new Bishop(4, 7, Color.BLACK);

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