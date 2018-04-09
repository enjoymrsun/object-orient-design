package cs5004.lab9.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Basket class to store a combination of stock shares.
 */
public class Basket {
  // stock ticker symbol => number of shares for that stock
  private Map<String, Integer> map;

  /**
   * New a Basket object.
   */
  public Basket() {
    this.map = new HashMap<>();
  }

  /**
   * Add a stock with volume to the basket.
   *
   * @param tickerSymbol   stock ticker symbol
   * @param volume stock volume
   */
  public void addStock(String tickerSymbol, Integer volume) {
    map.put(tickerSymbol, volume);
  }

  /**
   * Get a set of the stocks in the basket.
   */
  public Set<String> getStockSet() {
    return map.keySet();
  }

  /**
   * Get a stock volume in a basket.
   *
   * @param tickerSymbol stock name
   * @return volume number
   */
  public int getVolume(String tickerSymbol) {
    return map.get(tickerSymbol);
  }

}
