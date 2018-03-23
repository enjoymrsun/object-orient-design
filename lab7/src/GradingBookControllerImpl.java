import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Use to execute the code from GradingBookController class.
 */
public class GradingBookControllerImpl {
  /**
   * Main method to execute.
   *
   * @param args input arguments
   * @throws IOException when input cannot be found
   */
  public static void main(String[] args) throws IOException {
    try {
      FileInputStream f = new FileInputStream("./instructions.txt");
      new GradingBookControllerImplSimple(new InputStreamReader(f),
              System.out).menu(new GradingBookImpl());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}