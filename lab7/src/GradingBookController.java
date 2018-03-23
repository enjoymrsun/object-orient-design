import java.io.IOException;

/**
 * Provides methods to do the menu.
 */
public interface GradingBookController {
  /**
   * Prints menu to user to interact with.
   *
   * @param gb use to store grades
   * @throws IOException when input cannot be found
   */
  void menu(GradingBook gb) throws IOException;
}
