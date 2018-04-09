package cs5004.lab9.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import cs5004.lab9.controller.IActionListener;

/**
 * Class used to generate the whole view.
 */
public class JFrameView extends JFrame implements IView {
  private JPanel graphPanel;
  private JTextField sTickerSymbol;
  private JTextField sYear;
  private JTextField sMonth;
  private JTextField sDay;
  private JTextField eYear;
  private JTextField eMonth;
  private JTextField eDay;
  private JTextField showBuyOpp;
  private JTextField showTrend;
  private JButton submit;
  private JButton exit;

  /**
   * Constructor used to generate the GUI software.
   *
   * @param caption caption for the application.
   */
  public JFrameView(String caption) {
    super(caption);

    setSize(1600, 1430);
    setLocation(80, 30);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    this.setMinimumSize(new Dimension(1000, 800));

    this.setLayout(new FlowLayout());

    graphPanel = new JPanel();
    JLabel stockTickerSymbol = new JLabel("Stock Ticker Symbol");
    JLabel startYear = new JLabel("Start Year");
    JLabel startMonth = new JLabel("Month");
    JLabel startDay = new JLabel("Day");
    JLabel endYear = new JLabel("End Year");
    JLabel endMonth = new JLabel("Month");
    JLabel endDay = new JLabel("Day");
    JLabel buyOpp = new JLabel("Buy Opp");
    JLabel trend = new JLabel("Trend");

    sTickerSymbol = new JTextField(6);
    sYear = new JTextField(4);
    sMonth = new JTextField(2);
    sDay = new JTextField(2);
    eYear = new JTextField(4);
    eMonth = new JTextField(2);
    eDay = new JTextField(2);
    showBuyOpp = new JTextField(1);
    showTrend = new JTextField(1);

    submit = new JButton("submit");
    submit.setActionCommand("Submit Button");
    exit = new JButton("Exit");
    exit.setActionCommand("Exit Button");

    this.add(stockTickerSymbol);
    this.add(sTickerSymbol);
    this.add(startYear);
    this.add(sYear);
    this.add(startMonth);
    this.add(sMonth);
    this.add(startDay);
    this.add(sDay);
    this.add(endYear);
    this.add(eYear);
    this.add(endMonth);
    this.add(eMonth);
    this.add(endDay);
    this.add(eDay);
    this.add(buyOpp);
    this.add(showBuyOpp);
    this.add(trend);
    this.add(showTrend);
    this.add(submit);
    this.add(exit);

    this.add(graphPanel);

    pack();
    display();
  }

  @Override
  public Map<String, String> getInputArguments() {
    Map<String, String> arguments = new HashMap<>();

    arguments.put("STOCK_TICKER_SYMBOL", sTickerSymbol.getText());
    arguments.put("START_YEAR", sYear.getText());
    arguments.put("START_MONTH", sMonth.getText());
    arguments.put("START_DAY", sDay.getText());
    arguments.put("END_YEAR", eYear.getText());
    arguments.put("END_MONTH", eMonth.getText());
    arguments.put("END_DAY", eDay.getText());
    arguments.put("BUY_OPP", showBuyOpp.getText());
    arguments.put("TREND", showTrend.getText());

    return arguments;
  }

  @Override
  public void setListener(IActionListener listener) {
    ActionListener listen = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        listener.action(e.getActionCommand());
      }
    };
    submit.addActionListener(listen);
    exit.addActionListener(listen);
  }

  @Override
  public void draw(String imagePath) {
    // TODO how to draw a graph using SWING
    try {
      graphPanel.removeAll();
      BufferedImage myPicture = ImageIO.read(new File(imagePath));
      JLabel picLabel = new JLabel(new ImageIcon(myPicture));
      graphPanel.add(picLabel);
    } catch (Exception e) {
      System.err.println("Error occurs!");
    }
    display();
  }

  @Override
  public void display() {
    setVisible(true);
  }

}

