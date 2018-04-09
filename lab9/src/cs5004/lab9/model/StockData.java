package cs5004.lab9.model;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;

/**
 * A class used to get stock data for a given stock.
 */
public class StockData {
  private String tickerSymbol;
  // date YYYY-MM-DD => a single day price transaction
  private TreeMap<String, DailyData> data;

  /**
   * Constructor.
   *
   * @param tickerSymbol stock name as code
   */
  public StockData(String tickerSymbol) {
    this.tickerSymbol = tickerSymbol;
    this.data = new TreeMap<>();
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
  public String getTickerSymbol() {
    return tickerSymbol;
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
  public TreeMap<String, Double> getSubSetDataRange(String startDate, String endDate) {
    TreeMap<String, Double> map = new TreeMap<>();

    NavigableMap<String, DailyData> nMap = data.subMap(startDate, true, endDate, true);

    String key = nMap.firstKey();
    while (key != null) {
      map.put(key, data.get(key).getPrice());
      key = nMap.higherKey(key);
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
