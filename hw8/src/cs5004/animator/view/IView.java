package cs5004.animator.view;

import java.io.IOException;

/**
 * The view interface which includes Textual and SVG View.
 */
public interface IView {

  /**
   * Display this view.
   *
   * @param outputFile output file name
   * @param speed      the animation speed
   */
  void display(String outputFile, int speed) throws IOException;
}
