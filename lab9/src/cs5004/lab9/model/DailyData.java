package cs5004.lab9.model;

/**
 * This class represents a stock on a certain date.
 */
public class DailyData {
  private String stockTickerSymbol;
  private double open;
  private double high;
  private double low;
  private double close;
  private int volume;
  private String date;

  /**
   * Construct a stock object.
   *
   * @param stockTickerSymbol   stockTickerSymbol of the stock
   * @param date   date of the day
   * @param open   open price of the stock on that day
   * @param high   highest price of the stock on that day
   * @param low    lowest price of the stock on that day
   * @param close  close price of the stock on that day
   * @param volume volume of the stock on that day
   */
  public DailyData(String stockTickerSymbol, String date, double open, double high,
                   double low, double close, int volume) {
    this.stockTickerSymbol = stockTickerSymbol;
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
  }

  /**
   * Get close price.
   *
   * @return close price
   */
  public double getPrice() {
    return this.close;
  }

  /**
   * Stock code symbol.
   *
   * @return stock code
   */
  public String getStockTickerSymbol() {
    return stockTickerSymbol;
  }

  /**
   * Stock open price.
   *
   * @return stock open price for a date
   */
  public double getOpen() {
    return open;
  }

  /**
   * Stock highest price in a date.
   *
   * @return highest price in a date
   */
  public double getHigh() {
    return high;
  }

  /**
   * Stock lowest price in a date.
   *
   * @return lowest price in a date
   */
  public double getLow() {
    return low;
  }

  /**
   * Stock close price in a date.
   *
   * @return close price in a date
   */
  public double getClose() {
    return close;
  }

  /**
   * Stock volume in a date.
   *
   * @return a daily volume
   */
  public int getVolume() {
    return volume;
  }

  /**
   * Stock's daily date as a string.
   *
   * @return a daily date
   */
  public String getDate() {
    return date;
  }
}
