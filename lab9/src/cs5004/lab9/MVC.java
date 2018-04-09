import cs5004.lab9.controller.StockController;
import cs5004.lab9.model.AllData;
import cs5004.lab9.model.InvestingImpl;
import cs5004.lab9.model.InvestingModel;
import cs5004.lab9.model.StockApplication;
import cs5004.lab9.view.IView;
import cs5004.lab9.view.JFrameView;

/**
 * Overall MVC opener for the stock application.
 */
public class MVC {
  /**
   * Used to start the whole stock application.
   *
   * @param args args represent the companies' ticker symbol that you may add
   */
  public static void main(String[] args) {
    String[] stockSymbols = new String[]{"AAPL", "GOOG", "MSFT", "FB", "BABA", "AMZN"};

    try {
      AllData allData = StockApplication.getAllData(stockSymbols);
      InvestingModel model = new InvestingImpl();
      model.setAllData(allData);
      IView view = new JFrameView("Stock");

      StockController controller = new StockController(model, view);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      System.err.println("An error has occurred");
    }

  }

}
