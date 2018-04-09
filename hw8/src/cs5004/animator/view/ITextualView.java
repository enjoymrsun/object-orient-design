package cs5004.animator.view;

import cs5004.animator.AnimationImpl;
import java.io.IOException;

/**
 * The interface for our Textual view class.
 */
public interface ITextualView {
  /**
   * Print this view to PrintStream.
   *
   * @throws IOException
   */
  void print() throws IOException;
}
