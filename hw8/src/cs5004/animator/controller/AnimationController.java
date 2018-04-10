package cs5004.animator.controller;

import java.io.IOException;

import cs5004.animator.model.IAnimation;
import cs5004.animator.view.IView;

/**
 * This is the animation controller class.
 */
public class AnimationController {
  private IAnimation model;
  private IView view;

  /**
   * Construct an animation controller object.
   *
   * @param model the animation model
   * @param view  the view to display
   */
  public AnimationController(IAnimation model, IView view) {
    this.model = model;
    this.view = view;
  }

  /**
   * To display the animation in the view.
   *
   * @param outputFile the output file name
   * @param speed      speed of the animation (how many ticks per second)
   */
  public void display(String outputFile, int speed) {
    try {
      view.display(outputFile, speed);
    } catch (IOException e) {
      System.err.println("File IO Error!");
      System.exit(-1);
    }
  }
}

