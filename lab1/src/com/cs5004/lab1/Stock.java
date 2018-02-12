package com.cs5004.lab1;

/**
 * This class represents a Stock. The stock has a first name, a last name, year of birth, phone
 * number and email address.
 */
public class Stock {
  private String compName;
  private String symbol;
  private double price;
  private double lowestPrice;
  private double highestPrice;
  private long debt;
  private long assets;
  private int recomm;

  /**
   * Construct a com.cs5004.lab1.Stock object that has the provided company name, ticker symbol,
   * price per unit share, lowest price of that stock in the past 52 weeks, highest price of that
   * stock in the past 52 weeks, total debt owned by the company, total assets owned by the company,
   * analyst recommendation (1-5, 1 being strong sell and 5 being strong buy).
   *
   * @param compName  the company name
   * @param symbol the ticker symbol of this stock
   * @param price  the price of this stock
   * @param lowestPrice lowest price of that stock in the past 52 weeks
   * @param highestPrice highest price of that stock in the past 52 weeks
   * @param debt total debt owned by the company
   * @param assets total assets owned by the company
   * @param recomm analyst recommendation of this stock
   */
  public Stock(String compName, String symbol, double price, double lowestPrice,
               double highestPrice, long debt, long assets, int recomm) {
    this.compName = compName;
    this.symbol = symbol;
    this.price = price;
    this.lowestPrice = lowestPrice;
    this.highestPrice = highestPrice;
    this.debt = debt;
    this.assets = assets;
    this.recomm = recomm;
  }

  /**
   * Get the company name of this stock.
   *
   * @return the company name of the stock
   */
  public String getCompName() {
    return compName;
  }

  /**
   * Get the ticker symbol of this stock.
   *
   * @return the ticker symbol of the stock
   */
  public String getSymbol() {
    return symbol;
  }

  /**
   * Get the price per unit share of this stock.
   *
   * @return the price per unit share of the stock
   */
  public double getPrice() {
    return price;
  }

  /**
   * Get the lowest price in the past 52 weeks of this stock.
   *
   * @return lowest price in the past 52 weeks of this stock
   */
  public double getLowestPrice() {
    return lowestPrice;
  }

  /**
   * Get the highest price in the past 52 weeks of this stock.
   *
   * @return highest price in the past 52 weeks of this stock
   */
  public double getHighestPrice() {
    return highestPrice;
  }

  /**
   * Get the total debt owned by the company in dollar-cent amounts.
   *
   * @return the total debt owned by the company
   */
  public long getDebt() {
    return debt;
  }

  /**
   * Get the total assets owned by the company in dollar-cent amounts.
   *
   * @return the total assets owned by the company
   */
  public long getAssets() {
    return assets;
  }

  /**
   * Get the analyst recommendation of this stock.
   *
   * @return the analyst recommendation of this stock
   */
  public int getRecomm() {
    return recomm;
  }
}
