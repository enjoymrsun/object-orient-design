import org.junit.Before;
import org.junit.Test;

import java.rmi.UnexpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class contains all the unit tests for the Rook Chess Piece.
 */
public class RookTest {
  private Rook r1;
  private Rook r2;
  private Rook r3;

  /**
   * Constructor creates instances of class Rook.
   */
  @Before
  public void setUp() {
    r1 = new Rook(4, 4, Color.WHITE);
    r2 = new Rook(5, 2, Color.BLACK);
    r3 = new Rook(7, 1, Color.WHITE);
  }

  /**
   * Tests if method correctly throws Exception with wrong input coordinate.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    Rook r4 = new Rook(0, 8, Color.WHITE);
  }


  /**
   * Tests if method correctly returns the can move possibility.
   */
  @Test
  public void testCanMove() {
    assertTrue(r1.canMove(6, 4));
    assertTrue(r1.canMove(2, 4));
    assertFalse(r1.canMove(2, 2));
    assertTrue(r2.canMove(2, 2));
    assertTrue(r3.canMove(7, 0));
    assertFalse(r2.canMove(3, 1));
    assertFalse(r3.canMove(3, 4));
    assertTrue(r3.canMove(7, 1));
  }

  /**
   * Tests if method correctly returns the can kill possibility.
   */
  @Test
  public void testCanKill() {
    Knight k1 = new Knight(7, 4, Color.BLACK);
    Queen q1 = new Queen(3, 1, Color.WHITE);

    assertFalse(r1.canKill(r2));
    assertFalse(r2.canKill(r3));
    assertFalse(r1.canKill(r3));
    assertTrue(r1.canKill(k1));
    assertFalse(r3.canKill(q1));
    assertFalse(r2.canKill(q1));
    assertTrue(r2.canKill(new Queen(5, 6, Color.WHITE)));
  }

  /**
   * Test the consistency for the canKill and canMove methods.
   */
  @Test
  public void testConsistency1() {
    Rook cp1 = new Rook(2, 2, Color.WHITE);
    Rook cp2 = new Rook(2, 7, Color.BLACK);

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
    Rook cp1 = new Rook(2, 2, Color.WHITE);
    Rook cp2 = new Rook(5, 3, Color.BLACK);

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