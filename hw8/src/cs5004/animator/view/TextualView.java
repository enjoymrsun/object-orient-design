package cs5004.animator.view;
import cs5004.animator.*;
import cs5004.animator.AnimationImpl;

import java.io.IOException;
import java.io.PrintStream;
import java.io.FileOutputStream;

/**
 * The is the Textual view class.
 */
public class TextualView implements ITextualView {

  private AnimationImpl model;

  /**
   * Construct a textual view.
   *
   * @param model the animation model
   */
  public TextualView(AnimationImpl model) {
    this.model = model;
  }

  @Override
  public void print() throws IOException {

    FileOutputStream fout=new FileOutputStream("textual-view.txt");
    PrintStream pout=new PrintStream(fout);
    if(model.getAni().keySet().size() !=0) {
      pout.println("Shapes");
    }
    for (Shape s : model.getAni().keySet()) {
      pout.println("Name:" + s.getName());
      pout.println("Type:" + s.getType());
      if (s.getType().equals(ShapeType.RECTANGLE)) {
        pout.println("Min corner: (" + s.getPos()[0] + "," + s.getPos()[1]
                + "), Width: " + s.getScl()[0] + ", Height: " + s.getScl()[1]
                + ", Color: (" + s.getCol()[0] + "," + s.getCol()[1] + "," + s.getCol()[2] + ")");
      }
      else if (s.getType().equals(ShapeType.OVAL)) {
        pout.println("Center: (" + s.getPos()[0] + "," + s.getPos()[1]
                + "), X radius: " + s.getScl()[0] + ", Y radius: " + s.getScl()[1]
                + ", Color: (" + s.getCol()[0] + "," + s.getCol()[1] + "," + s.getCol()[2] + ")");
      }
      pout.println("Appears at t=" + s.getT1());
      pout.println("Disappears at t=" + s.getT2());
      pout.println("Disappears at t=" + s.getT2());
      pout.println();
    }

    // Print operations.
    for (Operation o : model.getOprs()) {
      if (o.getType().equals(OperationType.MOVE)) {
        pout.println("Shape " + o.getName() + " moves from (" + o.getStart()[0] + ","
                + o.getStart()[1] + ") to (" + o.getEnd()[0] + "," + o.getEnd()[1]
                + ") from t=" + o.getT1() + " to t=" + o.getT2());
      }
      else if (o.getType().equals(OperationType.SCALE)) {
        pout.println("Shape " + o.getName() + " scales from Width: " + o.getStart()[0]
                + ", Height: " + o.getStart()[1] + " to Width: " + o.getEnd()[0] + ", Height: "
                + o.getEnd()[1] + " from t=" + o.getT1() + " to t=" + o.getT2());
      }
      else if (o.getType().equals(OperationType.CHANGECOLOR)) {
        pout.println("Shape " + o.getName() + " changes color from ("
                + o.getStart()[0] + "," + o.getStart()[1] + "," + o.getStart()[2]
                + ") to (" + o.getEnd()[0] + "," + o.getEnd()[1] + "," + o.getEnd()[2]
                + ") from t=" + o.getT1() + " to t=" + o.getT2());
      }
    }
  }
}
