import java.util.Map;

/**
 * This is an interface represents an investing model.
 */
public interface InvestingModel {

  /**
   * Look up the price of a stock on a certain day.
   *
   * @param stock ticker symbol of the stock
   * @param date  the certain day
   * @return the price of a stock on a certain day
   */
  double lookUpPrice(String stock, String date);

  /**
   * Determine if there is a buying opportunity for a certain stock on a certain day.
   *
   * @param stock ticker symbol of the stock
   * @param date  the certain day
   * @return true if there is a buying opportunity for a certain stock on a certain day, no if not
   */
  boolean buyingOpportunity(String stock, String date);

  /**
   * Get historical (closing) prices for a stock for a certain date range. Historical prices are
   * available only for business days.
   *
   * @param stock ticker symbol of the stock
   * @param start start date of a certain date range
   * @param end   end date of a certain date range
   * @return the historical prices
   */
  Map<String, Double> historicalPrices(String stock, String start, String end);

  /**
   * Create a basket.
   *
   * @return the basket
   */
  Basket createBasket(Map<String, Integer> volumes);

  /**
   * Create a basket of stocks comprising of shares of one or more stocks and then determine its
   * price on a certain date.
   *
   * @param date the certain day
   * @return the basket's price on a certain date
   */
  double basketPrice(Basket basket, String date);

  /**
   * Determine if a basket trends up during a certain date range.
   *
   * @param basket a stock or a basket
   * @param start  start date of a certain date range
   * @param end    end date of a certain date range
   * @return if its trends up during the date range
   */
  boolean trendsUp(Basket basket, String start, String end);

  /**
   * Determine if a stock trends up during a certain date range.
   *
   * @param name  name of a stock
   * @param start start date of a certain date range
   * @param end   end date of a certain date range
   * @return if its trends up during the date range
   */
  boolean trendsUp(String name, String start, String end);
}
