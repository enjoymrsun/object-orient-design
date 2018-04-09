package cs5004.lab9.view;

import java.util.Map;

import cs5004.lab9.controller.IActionListener;

/**
 * View Interface that regulates what view can be used to show.
 */
public interface IView {
  /**
   * Get the view's arguments input from the GUI interface.
   *
   * @return arguments from the GUI interface
   */
  Map<String, String> getInputArguments();

  /**
   * Action listener that button needs to hear.
   *
   * @param listener action listener which receive actions
   */
  void setListener(IActionListener listener);

  /**
   * Let the frame to display the view.
   */
  void display();

  /**
   * Draw the given image into the GUI screen.
   *
   * @param imagePath image path needed to be draw on screen
   */
  void draw(String imagePath);
}
