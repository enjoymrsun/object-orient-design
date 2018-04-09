import java.util.HashMap;
import java.util.Map;

/**
 * This class contains all stock data.
 */
public class AllData {
  // stock symbol => all that stock data
  private Map<String, StockData> map;

  /**
   * Constructor to initialize map.
   */
  public AllData() {
    this.map = new HashMap<>();
  }

  /**
   * Add the stock data to the all data.
   *
   * @param sd the stock data needs to add
   */
  public void addStockData(StockData sd) {
    this.map.put(sd.getStockName(), sd);
  }

  /**
   * Return the stock data for a particular stock.
   *
   * @param stockName stock name
   * @return a stock data
   */
  public StockData getStockData(String stockName) {
    return map.get(stockName);
  }
}
