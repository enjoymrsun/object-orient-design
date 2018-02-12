import org.junit.Before;
import org.junit.Test;

import java.rmi.UnexpectedException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class contains all the unit tests for the Knight Chess Piece.
 */
public class KnightTest {
  private Knight k1;
  private Knight k2;
  private Knight k3;

  /**
   * Constructor creates instances of class Knight.
   */
  @Before
  public void setUp() {
    k1 = new Knight(4, 4, Color.WHITE);
    k2 = new Knight(5, 2, Color.BLACK);
    k3 = new Knight(7, 1, Color.WHITE);
  }

  /**
   * Tests if method correctly throws Exception with wrong input coordinate.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    Knight k4 = new Knight(-2, 0, Color.WHITE);
  }


  /**
   * Tests if method correctly returns the can move possibility.
   */
  @Test
  public void testCanMove() {
    assertTrue(k1.canMove(5, 6));
    assertTrue(k1.canMove(6, 5));
    assertTrue(k2.canMove(4, 4));
    assertFalse(k3.canMove(4, 4));
    assertFalse(k2.canMove(1, 4));
    assertFalse(k3.canMove(1, 4));
    assertTrue(k3.canMove(7, 1));
  }

  /**
   * Tests if method correctly returns the can kill possibility.
   */
  @Test
  public void testCanKill() {
    assertTrue(k1.canKill(k2));
    assertTrue(k2.canKill(k1));
    assertFalse(k3.canKill(k1));
    assertFalse(k1.canKill(new Knight(0, 0, Color.WHITE)));
    assertTrue(k2.canKill(k3));
    assertFalse(k3.canKill(new Queen(5, 2, Color.WHITE)));
  }

  /**
   * Test the consistency for the canKill and canMove methods.
   */
  @Test
  public void testConsistency1() {
    Knight cp1 = new Knight(2, 2, Color.WHITE);
    Knight cp2 = new Knight(4, 3, Color.BLACK);

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
    Knight cp1 = new Knight(2, 2, Color.WHITE);
    Knight cp2 = new Knight(4, 7, Color.BLACK);

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