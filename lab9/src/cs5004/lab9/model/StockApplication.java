package cs5004.lab9.model;

import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * Getting the all data for all stocks.
 */
public class StockApplication {

  /**
   * Get all stock data.
   *
   * @param companyTickerSymbols stock symbol as a array
   * @return all data for all stock
   * @throws Exception when cannot get that stock data
   */
  public static AllData getAllData(String[] companyTickerSymbols) throws Exception {
    AllData ad = new AllData();

    for (String company : companyTickerSymbols) {
      StockData sd = getOneStockDatas(company);
      ad.addStockData(sd);
    }

    return ad;
  }

  /**
   * Stock data for a given stock.
   *
   * @param companyTickerSymbol company code
   * @return stock data
   * @throws Exception when cannot get that stock data
   */
  private static StockData getOneStockDatas(String companyTickerSymbol) throws Exception {
    /*
     The URL below is to access the Alpha Vantage API.
     1. Run this program to see its output size
     2. Go to https://www.alphavantage.co/documentation/
     3. Try some of their examples using a web browser or replacing the URL below with it
     4. You will need to get your own API key. Look at the documentation web page for instructions
     5. Look at the format of the data it sends in response. Think about how you would parse
        this data (divide it into chunks so that you can read and make sense of it)
     6. Design wrappers around this service so that it helps your model
     */

    // historical data from vantage API
    // this company code should be updated
    String urlLink = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol="
            + companyTickerSymbol + "&apikey=7NYRGP7J40JTAY2W&datatype=csv&outputsize=full";
    URL url = new URL(urlLink);

    /*
     the line below "sends" this URL and receives a response, as an InputStream object

     This is equivalent to pasting this URL in a web browser and having the browser
     show you what it got in return

     Thus the line below is basically how you can do within a Java program what
     your web browser would normally do (communicate with another server,
     and show the output it received)
     */
    InputStream in;
    in = url.openStream();

    /*
     read this output as a string, word-by-word. You don't have to read it word by word
     necessarily, but doing so here as an example
     */
    Scanner sc = new Scanner(in);

    int index = 0;  // only get the recent 3000 daily data
    StockData sd = new StockData(companyTickerSymbol);
    String headline = sc.next();

    // get first 5000 trade days data
    while (sc.hasNext() && index < 5000) {
      String[] str = sc.next().split(",");
      String date = str[0];
      double open = Double.parseDouble(str[1]);
      double high = Double.parseDouble(str[2]);
      double low = Double.parseDouble(str[3]);
      double close = Double.parseDouble(str[4]);
      int volume = Integer.parseInt(str[5]);
      DailyData data = new DailyData(companyTickerSymbol, date, open, high, low, close, volume);
      sd.addDailyData(date, data);
      index++;
    }

    return sd;
  }
}
