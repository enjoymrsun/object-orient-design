package cs5004.animator.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.Operation;
import cs5004.animator.model.OperationType;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeType;

/**
 * The is the Textual view class.
 */
public class TextualView implements IView {

  private IAnimation model;

  /**
   * Construct a textual view.
   *
   * @param model the animation model
   */
  public TextualView(IAnimation model) {
    this.model = model;
  }

  @Override
  public void display(String outputFile, int speed) throws IOException {
    PrintStream pout = null;

    if ("out".equals(outputFile)) {
      pout = System.out;
    } else {
      FileOutputStream fout = new FileOutputStream(outputFile);
      pout = new PrintStream(fout);
    }

    if (model.getAni().keySet().size() != 0) {
      pout.println("Shapes");
    }
    for (Shape s : model.getAni().keySet()) {
      pout.println("Name:" + s.getName());
      pout.println("Type:" + s.getType());
      if (s.getType().equals(ShapeType.RECTANGLE)) {
        pout.println("Min corner: (" + s.getPos()[0] + "," + s.getPos()[1]
                + "), Width: " + s.getScl()[0] + ", Height: " + s.getScl()[1]
                + ", Color: (" + s.getCol()[0] + "," + s.getCol()[1] + "," + s.getCol()[2] + ")");
      } else if (s.getType().equals(ShapeType.OVAL)) {
        pout.println("Center: (" + s.getPos()[0] + "," + s.getPos()[1]
                + "), X radius: " + s.getScl()[0] + ", Y radius: " + s.getScl()[1]
                + ", Color: (" + s.getCol()[0] + "," + s.getCol()[1] + "," + s.getCol()[2] + ")");
      }
      pout.println("Appears at t=" + s.getT1() / speed + "s");
      pout.println("Disappears at t=" + s.getT2() / speed + "s");
      pout.println();
    }

    List<List<Operation>> lists = new ArrayList<>(model.getAni().values());
    List<Operation> list = lists.stream().flatMap(Collection::stream).collect(Collectors.toList());
    list.sort(Comparator.comparing(Operation::getT1));

    // Print operations.
    for (Operation o : list) {
      if (o.getType().equals(OperationType.MOVE)) {
        pout.println("Shape " + o.getName() + " moves from (" + o.getStart()[0] + ","
                + o.getStart()[1] + ") to (" + o.getEnd()[0] + "," + o.getEnd()[1]
                + ") from t=" + o.getT1() / speed + "s to t=" + o.getT2() / speed + "s");
      } else if (o.getType().equals(OperationType.SCALE)) {
        pout.println("Shape " + o.getName() + " scales from Width: " + o.getStart()[0]
                + ", Height: " + o.getStart()[1] + " to Width: " + o.getEnd()[0] + ", Height: "
                + o.getEnd()[1] + " from t=" + o.getT1() / speed + "s to t=" + o.getT2() / speed + "s");
      } else if (o.getType().equals(OperationType.CHANGECOLOR)) {
        pout.println("Shape " + o.getName() + " changes color from ("
                + o.getStart()[0] + "," + o.getStart()[1] + "," + o.getStart()[2]
                + ") to (" + o.getEnd()[0] + "," + o.getEnd()[1] + "," + o.getEnd()[2]
                + ") from t=" + o.getT1() / speed + "s to t=" + o.getT2() / speed + "s");
      }
    }
  }
}
