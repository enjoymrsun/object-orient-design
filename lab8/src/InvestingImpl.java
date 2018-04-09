import java.util.Map;
import java.util.Set;

/**
 * This class implements the InvestingModel Interface.
 */
public class InvestingImpl implements InvestingModel {
  private AllData allData;

  /**
   * Constructor.
   */
  public InvestingImpl() {
    this.allData = new AllData();
  }

  /**
   * Set the all data for all stocks.
   *
   * @param allData all stock data
   */
  public void setAllData(AllData allData) {
    this.allData = allData;
  }

  @Override
  public double lookUpPrice(String stock, String date) {
    return allData.getStockData(stock).getStockPriceOnDate(date);
  }

  @Override
  public boolean buyingOpportunity(String stock, String date) {
    return allData.getStockData(stock).haveBuyOpportunity(date);
  }

  @Override
  public Map<String, Double> historicalPrices(String stock, String start, String end) {
    return allData.getStockData(stock).getSubSetDataRange(start, end);
  }

  @Override
  public Basket createBasket(Map<String, Integer> volumes) {
    Basket basket = new Basket();

    for (Map.Entry<String, Integer> singleStock : volumes.entrySet()) {
      basket.addStock(singleStock.getKey(), singleStock.getValue());
    }

    return basket;
  }

  @Override
  public double basketPrice(Basket basket, String date) {
    double price;
    double result = 0;

    Set<String> keySet = basket.getStockSet();
    for (String s : keySet) {
      price = this.allData.getStockData(s).getDailyData(date).getPrice();
      result += price * basket.getVolume(s);
    }

    return result;
  }

  @Override
  public boolean trendsUp(Basket basket, String start, String end) {
    return basketPrice(basket, end) > basketPrice(basket, start);
  }

  @Override
  public boolean trendsUp(String name, String start, String end) {
    return this.allData.getStockData(name).getDailyData(end).getPrice()
            > this.allData.getStockData(name).getDailyData(start).getPrice();
  }
}
