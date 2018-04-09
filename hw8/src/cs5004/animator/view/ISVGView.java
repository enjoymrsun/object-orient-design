package cs5004.animator.view;

import java.io.IOException;

/**
 * The interface for our SVG view class.
 */
public interface ISVGView {

  /**
   * Print this view to SVG file.
   *
   * @throws IOException
   */
  void createSVG() throws IOException;
}
