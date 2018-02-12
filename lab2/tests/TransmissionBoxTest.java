import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TransmissionBoxTest {
  private TransmissionBox tb1;
  private TransmissionBox tb2;
  private TransmissionBox tb8;
  private TransmissionBox tb9;

  /**
   * Create 2 TransmissionBox Objects used to test.
   */
  @Before
  public void setUp() {
    tb1 = new TransmissionBox(34, 20, 30, 40, 50);
    tb2 = new TransmissionBox(30, 20, 30, 40, 50);
    tb8 = new TransmissionBox(39, 20, 30, 40, 50);
    tb9 = new TransmissionBox(41, 20, 30, 40, 50);
  }

  /**
   * Test whether the constructor throws exception when typing in wrong input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    TransmissionBox tb3;
    tb3 = new TransmissionBox(-20, 20, 30, 40, 50);
  }

  /**
   * Test whether the constructor throws exception when typing in wrong input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    TransmissionBox tb4;
    tb4 = new TransmissionBox(20, -10, 30, 40, 50);
  }

  /**
   * Test whether the constructor throws exception when typing in wrong input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    TransmissionBox tb5;
    tb5 = new TransmissionBox(20, 30, 22, 40, 50);
  }

  /**
   * Test whether the constructor throws exception when typing in wrong input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor4() {
    TransmissionBox tb6;
    tb6 = new TransmissionBox(20, -10, 50, 40, 50);
  }

  /**
   * Test whether the constructor throws exception when typing in wrong input.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor5() {
    TransmissionBox tb7;
    tb7 = new TransmissionBox(20, -10, 30, 40, 20);
  }

  @Test
  public void testGetSpeedStatus() {
    assertEquals(34, tb1.getSpeedStatus());
    assertEquals(30, tb2.getSpeedStatus());
    assertEquals(39, tb8.getSpeedStatus());
  }

  @Test
  public void testGetGearStatus() {
    assertEquals(3, tb1.getGearStatus());
    assertEquals(2, tb2.getGearStatus());
    assertEquals(4, tb8.increaseSpeed().getGearStatus());
    assertEquals(3, tb9.decreaseSpeed().getGearStatus());
  }


  @Test
  public void testToString() {
    assertEquals("Current Speed:34. Gear Level:3.", tb1.toString());
    assertEquals("Current Speed:30. Gear Level:2.", tb2.toString());
    assertEquals("Current Speed:41. Gear Level:4.", tb9.toString());
  }

  @Test
  public void testIncreaseSpeed() {
    assertEquals(36, tb1.increaseSpeed().getSpeedStatus());
    assertEquals(32, tb2.increaseSpeed().getSpeedStatus());
    assertEquals(41, tb8.increaseSpeed().getSpeedStatus());
  }

  @org.junit.Test
  public void testDecreaseSpeed() {
    assertEquals(32, tb1.decreaseSpeed().getSpeedStatus());
    assertEquals(28, tb2.decreaseSpeed().getSpeedStatus());
    assertEquals(39, tb9.decreaseSpeed().getSpeedStatus());
  }

}