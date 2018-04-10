package cs5004.animator;

import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JFrame;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.IAnimation;
import cs5004.animator.view.IView;
import cs5004.animator.view.SVGView;
import cs5004.animator.view.TextualView;

/**
 * The easyanimator application.
 */
public final class EasyAnimator {

  /**
   * The main method will be the entry point for the program. It takes inputs as command-line
   * arguments.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    for (int i = 0; i < args.length; i++) {
      System.out.println("No." + i + " argument is: " + args[i]);
    }

    if (args.length > 8 || args.length < 4) {
      JOptionPane.showMessageDialog(new JFrame("Error"),
              "You have too many or too little arguments!",
              "Error", JOptionPane.ERROR_MESSAGE);
      System.exit(-1);
    }

    String animationFile = "";
    String typeOfView = "";
    String outputFile = null;
    int speed = 1;
    int i = 0;

    while (i < args.length && args[i].startsWith("-")) {
      if (args[i].equals("-speed")) {
        try {
          speed = Integer.parseInt(args[++i]);
        } catch (NumberFormatException e) {
          System.err.println("Your Speed for Animation is incorrect!");
          System.exit(-1);
        }
      } else if (args[i].equals("-if")) {
        animationFile = args[++i];
      } else if (args[i].equals("-iv")) {
        typeOfView = args[++i];
      } else if (args[i].equals("-o")) {
        outputFile = args[++i];
      } else {
        JOptionPane.showMessageDialog(new JFrame("Error"),
                "ParseCmdLine: illegal option, software cannot understand.",
                "Error", JOptionPane.ERROR_MESSAGE);
        System.exit(-1);
      }
      ++i;
    }

    IAnimation model = null;

    try {
      TweenModelBuilder<IAnimation> builder = new AnimationImpl.Builder();
      AnimationFileReader afr = new AnimationFileReader();
      model = afr.readFile(animationFile, builder);
    } catch (IOException e) {
      System.err.println("File IO Error!");
      System.exit(-1);
    }

    IView view = null;
    if ("svg".equals(typeOfView)) {
      view = new SVGView(model);
    } else if ("text".equals(typeOfView)) {
      view = new TextualView(model);
    } else {
      System.err.println("File Type Error!");
      System.exit(-1);
    }

    AnimationController controller = new AnimationController(model, view);

    if (outputFile == null) {
      outputFile = "out";
    }

    controller.display(outputFile, speed);
  }
}