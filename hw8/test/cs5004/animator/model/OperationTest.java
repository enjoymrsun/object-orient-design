package cs5004.animator.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This class tests whether the methods on operation class is correct or not.
 */
public class OperationTest {
  private Operation operationForPos;
  private Operation operationForScl;
  private Operation operationForCol;

  /**
   * Prepare 3 kinds of operation instance to test their related methods.
   */
  @Before
  public void setUp() throws Exception {
    operationForPos = new Operation("pos_opr", OperationType.MOVE,
            new float[]{123.12f, 73.13f}, new float[]{65.23f, 76.13f}, 14, 50);
    operationForScl = new Operation("scl_opr", OperationType.SCALE,
            new float[]{65.12f, 24.13f}, new float[]{99.23f, 45.13f}, 225, 515);
    operationForCol = new Operation("col_opr", OperationType.CHANGECOLOR,
            new float[]{0.1251f, 0.1386f, 0.7381f},
            new float[]{0.6523f, 0.7613f, 0.1415f}, 51, 99);
  }

  /**
   * Test the operation's String getName() method.
   */
  @Test
  public void testGetName() {
    assertEquals("pos_opr", operationForPos.getName());
    assertEquals("scl_opr", operationForScl.getName());
    assertEquals("col_opr", operationForCol.getName());
  }

  /**
   * Test the operation's OperationType getType() method.
   */
  @Test
  public void testGetType() {
    assertEquals(OperationType.MOVE, operationForPos.getType());
    assertEquals(OperationType.SCALE, operationForScl.getType());
    assertEquals(OperationType.CHANGECOLOR, operationForCol.getType());
  }

  /**
   * Test the operation's float[] getStart() method.
   */
  @Test
  public void testGetStart() {
    assertArrayEquals(new float[]{123.12f, 73.13f}, operationForPos.getStart(), 0.0001f);
    assertArrayEquals(new float[]{65.12f, 24.13f}, operationForScl.getStart(), 0.0001f);
    assertArrayEquals(new float[]{0.1251f, 0.1386f, 0.7381f},
            operationForCol.getStart(), 0.0001f);
  }

  /**
   * Test the operation's float[] getEnd() method.
   */
  @Test
  public void testGetEnd() {
    assertArrayEquals(new float[]{65.23f, 76.13f}, operationForPos.getEnd(), 0.0001f);
    assertArrayEquals(new float[]{99.23f, 45.13f}, operationForScl.getEnd(), 0.0001f);
    assertArrayEquals(new float[]{0.6523f, 0.7613f, 0.1415f},
            operationForCol.getEnd(), 0.0001f);
  }

  /**
   * Test the operation's int getT1() method.
   */
  @Test
  public void testGetT1() {
    assertEquals(14, operationForPos.getT1());
    assertEquals(225, operationForScl.getT1());
    assertEquals(51, operationForCol.getT1());
  }

  /**
   * Test the operation's int getT2() method.
   */
  @Test
  public void testGetT2() {
    assertEquals(50, operationForPos.getT2());
    assertEquals(515, operationForScl.getT2());
    assertEquals(99, operationForCol.getT2());
  }

}