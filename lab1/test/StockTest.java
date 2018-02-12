import com.cs5004.lab1.Stock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class StockTest {
  private Stock stock;

  /**
   * Generate 1 stock for test.
   */
  @Before
  public void setUp() {
    stock = new Stock("abc company", "ACC", 77.88, 34.12,
            99.09, 567023000, 576033000, 4);
  }

  /**
   * Tests whether getCompName() method in Stock class is right or not.
   */
  @Test
  public void testCompName() throws Exception {
    assertEquals(stock.getCompName(), "abc company");
    assertNotEquals(stock.getCompName(), "acc company");
  }

  /**
   * Tests whether getSymbol() method in Stock class is right or not.
   */
  @Test
  public void testSymbol() throws Exception {
    assertEquals(stock.getSymbol(), "ACC");
    assertNotEquals(stock.getSymbol(), "CCA");
  }

  /**
   * Tests whether getPrice() method in Stock class is right or not.
   */
  @Test
  public void testPrice() throws Exception {
    assertEquals(stock.getPrice(), 77.88, 0.0001);
    assertNotEquals(stock.getPrice(), 77.90, 0.0001);
  }

  /**
   * Tests whether getLowestPrice() method in Stock class is right or not.
   */
  @Test
  public void testLowestPrice() throws Exception {
    assertEquals(stock.getLowestPrice(), 34.12, 0.0001);
    assertNotEquals(stock.getLowestPrice(), 34.11, 0.0001);
  }

  /**
   * Tests whether getHighestPrice() method in Stock class is right or not.
   */
  @Test
  public void testHighestPrice() throws Exception {
    assertEquals(stock.getHighestPrice(), 99.09, 0.0001);
    assertNotEquals(stock.getHighestPrice(), 99.10, 0.0001);
  }

  /**
   * Tests whether getDebt() method in Stock class is right or not.
   */
  @Test
  public void testDebt() throws Exception {
    assertEquals(stock.getDebt(), 567023000);
    assertNotEquals(stock.getDebt(), 567023020);
  }

  /**
   * Tests whether getAssets() method in Stock class is right or not.
   */
  @Test
  public void testAssets() throws Exception {
    assertEquals(stock.getAssets(), 576033000);
    assertNotEquals(stock.getAssets(), 576033001);
  }

  /**
   * Tests whether getRecomm() method in Stock class is right or not.
   */
  @Test
  public void testRecomm() throws Exception {
    assertEquals(stock.getRecomm(), 4);
    assertNotEquals(stock.getRecomm(), 5);
  }

}