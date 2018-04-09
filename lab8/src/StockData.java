import java.util.HashMap;
import java.util.Map;

/**
 * A class used to get stock data for a given stock.
 */
public class StockData {
  private String stockName;
  // date YYYY-MM-DD => a single day price transaction
  private Map<String, DailyData> data;

  /**
   * Constructor.
   *
   * @param stockName stock name as code
   */
  public StockData(String stockName) {
    this.stockName = stockName;
    this.data = new HashMap<>();
  }

  /**
   * Add a daily data for a stock.
   *
   * @param date date
   * @param d    daily data
   */
  public void addDailyData(String date, DailyData d) {
    data.put(date, d);
  }

  /**
   * Stock name for this stock.
   *
   * @return stock name
   */
  public String getStockName() {
    return stockName;
  }

  /**
   * Get the stock close price for a date.
   *
   * @param date date you want to get
   * @return stock price
   */
  public double getStockPriceOnDate(String date) {
    return data.get(date).getPrice();
  }

  /**
   * Get a price set with the given date range.
   *
   * @param startDate start date
   * @param endDate   end date
   * @return stock price set
   */
  public Map<String, Double> getSubSetDataRange(String startDate, String endDate) {
    Map<String, Double> map = new HashMap<>();

    for (Map.Entry<String, DailyData> singleStock : data.entrySet()) {
      String nowDate = singleStock.getKey();
      if (startDate.compareTo(nowDate) <= 0 && nowDate.compareTo(endDate) <= 0) {
        map.put(nowDate, singleStock.getValue().getPrice());
      }
    }

    return map;
  }

  /**
   * Get a stock's data on one day.
   *
   * @param date date you want to check
   * @return daily data
   */
  public DailyData getDailyData(String date) {
    return data.get(date);
  }

  /**
   * Does the stock on a date has a buying opportunity.
   *
   * @param date date you want to examine
   * @return whether there is a buying opportunity or not
   */
  public boolean haveBuyOpportunity(String date) {
    String[] dateParts = date.split("-");
    int year = Integer.parseInt(dateParts[0]);
    int month = Integer.parseInt(dateParts[1]);
    int day = Integer.parseInt(dateParts[2]);

    String daysOf50Before = DateUtil.getBeforeDate(year, month, day, 50);
    String daysOf200Before = DateUtil.getBeforeDate(year, month, day, 200);

    double moving50average = movingAverage(getSubSetDataRange(daysOf50Before, date));
    double moving200average = movingAverage(getSubSetDataRange(daysOf200Before, date));


    return moving50average > moving200average ? true : false;
  }

  /**
   * Calculate the moving average with a given price values.
   *
   * @param map the price map we get
   * @return the moving average
   */
  private double movingAverage(Map<String, Double> map) {
    double sum = 0;
    for (double d : map.values()) {
      sum += d;
    }

    return sum / (map.size());
  }
}
