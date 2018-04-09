package cs5004.lab9.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import cs5004.lab9.model.InvestingModel;
import cs5004.lab9.model.StockGraphModel;
import cs5004.lab9.view.IView;

/**
 * Controller to connect with View and Stock data model to perform action related to user behavior.
 */
public class StockController implements IActionListener {
  private InvestingModel model;
  private IView view;

  /**
   * Controller to connect View and Model.
   *
   * @param m model used for stock data model
   * @param v view used to present the stock trend
   */
  public StockController(InvestingModel m, IView v) {
    model = m;
    view = v;
    view.setListener(this);
    view.display();
  }

  /**
   * Transform number to the correct date number form.
   *
   * @param num input num as string
   * @return correct form
   */
  private String int2String(String num) {
    int n = Integer.parseInt(num);
    StringBuilder sb = new StringBuilder();

    if (n < 10) {
      sb.append(0);
    }

    sb.append(n);

    return sb.toString();
  }

  /**
   * Transform number to the boolean as true or false.
   *
   * @param num input num as string
   * @return true or false for the input num string
   */
  private boolean str2Bool(String num) {
    return !"0".equals(num);
  }

  /**
   * Perform action when a button is clicked.
   *
   * @param e action the button is presented
   */
  @Override
  public void action(String e) {
    switch (e) {
      case "Submit Button":
        Map<String, String> arguments = view.getInputArguments();

        String startYear = arguments.get("START_YEAR");
        String startMonth = int2String(arguments.get("START_MONTH"));
        String startDay = int2String(arguments.get("START_DAY"));
        String startDate = startYear + "-" + startMonth + "-" + startDay;

        String endYear = arguments.get("END_YEAR");
        String endMonth = int2String(arguments.get("END_MONTH"));
        String endDay = int2String(arguments.get("END_DAY"));
        String endDate = endYear + "-" + endMonth + "-" + endDay;

        String stockTickerSymbol = arguments.get("STOCK_TICKER_SYMBOL");
        boolean needBuyOpp = str2Bool(arguments.get("BUY_OPP"));
        boolean needTrend = str2Bool(arguments.get("TREND"));

        StockGraphModel sGM = new StockGraphModel();
        TreeMap<String, Double> dataPoints =
                model.historicalPrices(stockTickerSymbol, startDate, endDate);
        sGM.setStockTickerSymbol(stockTickerSymbol);
        sGM.setSize(dataPoints.size());
        double minPrice = Double.MAX_VALUE;
        double maxPrice = Double.MIN_VALUE;
        Map<String, Boolean> dataPointsForBuyOpp = new HashMap<>();
        for (Map.Entry<String, Double> data : dataPoints.entrySet()) {
          minPrice = Math.min(minPrice, data.getValue());
          maxPrice = Math.max(maxPrice, data.getValue());
          dataPointsForBuyOpp.put(data.getKey(),
                  model.buyingOpportunity(stockTickerSymbol, data.getKey()));
        }
        sGM.setMaxPrice(maxPrice);
        sGM.setMinPrice(minPrice);
        sGM.setNeedBuyOpp(needBuyOpp);
        sGM.setNeedTrend(needTrend);
        sGM.setDataPoints(dataPoints);
        sGM.setDataPointsBuyOpp(dataPointsForBuyOpp);

        String graphInputFile = Plotter.plot(sGM);
        view.draw(graphInputFile);

        break;
      case "Exit Button":
        System.exit(0);
        break;
      default:
        break;
    }
  }

}

