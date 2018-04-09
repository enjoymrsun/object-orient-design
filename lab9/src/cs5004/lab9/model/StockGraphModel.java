package cs5004.lab9.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * Model used to draw stock price graph.
 */
public class StockGraphModel {
  private String stockTickerSymbol;
  private int size;
  private double maxPrice;
  private double minPrice;
  private boolean needBuyOpp;
  private boolean needTrend;
  private TreeMap<String, Double> dataPoints;
  private Map<String, Boolean> dataPointsBuyOpp;

  /**
   * Get the stock ticker symbol for this stock graph.
   *
   * @return ticker symbol as string
   */
  public String getStockTickerSymbol() {
    return stockTickerSymbol;
  }

  /**
   * Set the stock ticker symbol for this stock graph.
   *
   * @param stockTickerSymbol ticker symbol as string
   */
  public void setStockTickerSymbol(String stockTickerSymbol) {
    this.stockTickerSymbol = stockTickerSymbol;
  }

  /**
   * Get the data points' size for this stock model.
   *
   * @return data points' size for this stock model
   */
  public int getSize() {
    return size;
  }

  /**
   * Set the data points' size for this stock model.
   *
   * @param size data points' size for this stock model
   */
  public void setSize(int size) {
    this.size = size;
  }

  /**
   * Get the highest price among all the stock data points.
   *
   * @return highest price among all the stock data points
   */
  public double getMaxPrice() {
    return maxPrice;
  }

  /**
   * Set the highest price among all the stock data points.
   *
   * @param maxPrice highest price among all the stock data points
   */
  public void setMaxPrice(double maxPrice) {
    this.maxPrice = maxPrice;
  }

  /**
   * Get the lowest price among all the stock data points.
   *
   * @return lowest price among all the stock data points
   */
  public double getMinPrice() {
    return minPrice;
  }

  /**
   * Set the lowest price among all the stock data points.
   *
   * @param minPrice lowest price among all the stock data points
   */
  public void setMinPrice(double minPrice) {
    this.minPrice = minPrice;
  }

  /**
   * Get the graph whether needs to show buy opportunity points.
   *
   * @return whether needs to show buy opportunity points
   */
  public boolean isNeedBuyOpp() {
    return needBuyOpp;
  }

  /**
   * Set the graph whether needs to show buy opportunity points.
   *
   * @param needBuyOpp whether needs to show buy opportunity points
   */
  public void setNeedBuyOpp(boolean needBuyOpp) {
    this.needBuyOpp = needBuyOpp;
  }

  /**
   * Get the graph whether needs to show trend.
   *
   * @return whether needs to show trend
   */
  public boolean isNeedTrend() {
    return needTrend;
  }

  /**
   * Set the graph whether needs to show trend.
   *
   * @param needTrend whether needs to show trend
   */
  public void setNeedTrend(boolean needTrend) {
    this.needTrend = needTrend;
  }

  /**
   * Get the data points for the stock in a given date range.
   *
   * @return all the data points as a TreeMap
   */
  public TreeMap<String, Double> getDataPoints() {
    return dataPoints;
  }

  /**
   * Set the data points for the stock in a given date range.
   *
   * @param dataPoints all the data points as a TreeMap
   */
  public void setDataPoints(TreeMap<String, Double> dataPoints) {
    this.dataPoints = dataPoints;
  }

  /**
   * Get the map for each data points whether it has a buy opportunity on that date or not.
   *
   * @return map for each data points whether it has a buy opportunity on that date or not
   */
  public Map<String, Boolean> getDataPointsBuyOpp() {
    return dataPointsBuyOpp;
  }

  /**
   * Set the map for each data points whether it has a buy opportunity on that date or not.
   *
   * @param dataPointsBuyOpp for each data points whether it has a buy opportunity on that date
   */
  public void setDataPointsBuyOpp(Map<String, Boolean> dataPointsBuyOpp) {
    this.dataPointsBuyOpp = dataPointsBuyOpp;
  }
}
