package cs5004.lab9.controller;

import java.awt.Color;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import cs5004.lab9.model.StockGraphModel;

/**
 * Plotter object used to plot a picture to the Swing GUI software.
 */
public class Plotter {

  /**
   * Resize a number with in the given range compared with other accompanied points.
   *
   * @param i   number you want to resize
   * @param min minimum number in all numbers
   * @param max max number in all numbers
   * @param m   min range number
   * @param n   max range number
   * @return a transformed result number
   */
  private static double resize(double i, double min, double max, int m, int n) {
    return (n - m) * (i - min) / (max - min) + m;
  }

  /**
   * Round a number to nearest position.
   *
   * @param d number to be rounded
   * @return nearest integer
   */
  private static int round(double d) {
    int num = (int) d;
    if ((d - num) > (num + 1 - d)) {
      return num + 1;
    } else {
      return num;
    }
  }

  /**
   * Graph model used to plot the stock image.
   *
   * @param model input graph model
   * @return result image path
   */
  public static String plot(StockGraphModel model) {
    ImagePlotter plotter = new ImagePlotter();
    int count = model.getSize();
    plotter.setWidth(1000);
    plotter.setHeight(700);

    // Y-axis [0 ... (100, 600) ... 700]
    // X-axis [0 ... (50, 950) ... 1000]

    plotter.setDimensions(0, 1000, 0, 700);

    int idx = 1;
    TreeMap<String, Double> dataPoints = model.getDataPoints();
    Map<String, Boolean> dataPointsBuyOpp = model.getDataPointsBuyOpp();
    String date = dataPoints.firstKey();
    double minPrice = model.getMinPrice();
    double maxPrice = model.getMaxPrice();
    boolean needBuyOpp = model.isNeedBuyOpp();
    boolean needTrend = model.isNeedTrend();
    String startDate = dataPoints.firstKey();
    String endDate = dataPoints.lastKey();
    String tickerSymbol = model.getStockTickerSymbol();

    while (date != null) {
      double priceY = resize(dataPoints.get(date), minPrice, maxPrice, 100, 600);
      double indexX = resize(idx, 1, count, 50, 950);
      if (needBuyOpp) {
        // 有 Buy Opp 的画橙色点
        if (dataPointsBuyOpp.get(date)) {
          plotter.addPoint(round(indexX), round(priceY), Color.orange);
        } else {
          plotter.addPoint(round(indexX), round(priceY), Color.blue);
        }
      } else {
        plotter.addPoint(round(indexX), round(priceY), Color.blue);
      }

      date = dataPoints.higherKey(date);
      idx++;
    }

    int startX = round(resize(1, 1, count, 50, 950));
    int startY = round(resize(dataPoints.get(startDate), minPrice, maxPrice, 100, 600));
    int endX = round(resize(count, 1, count, 50, 950));
    int endY = round(resize(dataPoints.get(endDate), minPrice, maxPrice, 100, 600));


    if (needTrend) {
      plotter.addLine(startX, startY, endX, endY);
    }

    String resultPath = "./images/" + tickerSymbol + "_" + startDate + "_" + endDate + ".png";

    try {
      plotter.write(resultPath);
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }

    return resultPath;
  }
}

