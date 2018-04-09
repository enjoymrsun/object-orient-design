import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Basket class to store a combination of stock shares.
 */
public class Basket {
  // stock symbol => number of shares for that stock
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
   * @param name   stock name
   * @param volume stock volume
   */
  public void addStock(String name, Integer volume) {
    map.put(name, volume);
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
   * @param name stock name
   * @return volume number
   */
  public int getVolume(String name) {
    return map.get(name);
  }

}
