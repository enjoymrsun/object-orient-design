import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test Investing Implementation.
 */
public class InvestingImplTest {
  private InvestingImpl ii;

  /**
   * Initialize a InvestingImpl object.
   *
   * @throws Exception when cannot read stock data
   */
  @Before
  public void setUp() throws Exception {
    String[] companies = new String[]{"AAPL", "MSFT"};
    AllData ad = StockApplication.getAllData(companies);
    ii = new InvestingImpl();
    ii.setAllData(ad);
  }

  /**
   * Test double lookUpPrice(String stock, String date) method.
   */
  @Test
  public void lookUpPrice() {
    assertEquals(89.79, ii.lookUpPrice("MSFT", "2018-03-22"), 0.0001);
    assertEquals(175.01, ii.lookUpPrice("AAPL", "2017-12-21"), 0.0001);
  }

  /**
   * Test boolean buyingOpportunity(String stock, String date) method.
   */
  @Test
  public void buyingOpportunity() {
    assertTrue(ii.buyingOpportunity("AAPL", "2017-10-02"));
    assertTrue(ii.buyingOpportunity("MSFT", "2013-12-09"));
  }

  /**
   * Test Map historicalPrices(String stock, String start, String end) method.
   */
  @Test
  public void historicalPrices() {
    // date => stock daily data
    Map<String, Double> res = ii.historicalPrices("MSFT", "2016-07-22", "2018-03-30");
    assertTrue(res.containsKey("2016-11-01"));
    assertTrue(res.containsKey("2017-06-22"));
  }

  /**
   * Test double basketPrice(Basket basket, String date) method.
   */
  @Test
  public void basketPrice() {
    Map<String, Integer> combination = new HashMap<>();
    combination.put("AAPL", 1000);
    combination.put("MSFT", 500);
    Basket b = ii.createBasket(combination);
    assertEquals(195615.0, ii.basketPrice(b, "2017-08-24"), 0.00001);
  }

  /**
   * Test boolean trendsUp(Basket basket, String start, String end) method. Test boolean
   * trendsUp(String name, String start, String end) method.
   */
  @Test
  public void trendsUp() {
    Map<String, Integer> combination = new HashMap<>();
    combination.put("AAPL", 1000);
    combination.put("MSFT", 500);
    Basket b = ii.createBasket(combination);
    assertTrue(ii.trendsUp(b, "2015-01-29", "2017-08-24"));
    assertTrue(ii.trendsUp("MSFT", "2015-03-10", "2018-03-22"));
    assertTrue(ii.trendsUp("AAPL", "2015-03-20", "2018-03-05"));
  }

}