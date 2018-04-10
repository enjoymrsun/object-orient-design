package cs5004.animator.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import cs5004.animator.model.IAnimation;
import cs5004.animator.model.Operation;
import cs5004.animator.model.OperationType;
import cs5004.animator.model.Shape;
import cs5004.animator.model.ShapeType;

/**
 * The is the SVG view class.
 */
public class SVGView implements IView {
  private IAnimation model;

  /**
   * Construct a textual view.
   *
   * @param model the animation model
   */
  public SVGView(IAnimation model) {
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

    pout.println("<svg width=\"1800\" height=\"1500\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (Shape s : model.getAni().keySet()) {
      if (s.getType().equals(ShapeType.RECTANGLE)) {
        pout.printf("<rect id=\"%s\" x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" height=\"%.1f\" " +
                        "fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
                s.getName(), s.getPos()[0], s.getPos()[1], 0.0, 0.0,
                (int) (s.getCol()[0]*255), (int) (s.getCol()[1]*255), (int) (s.getCol()[2]*255));
        pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%fs\" " +
                        "attributeName=\"width\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT1() / speed, 0.1, s.getScl()[0]);
        pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%fs\" " +
                        "attributeName=\"height\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT1() / speed, 0.1, s.getScl()[1]);

        for (Operation o : model.getAni().get(s)) {
          if (o.getType().equals(OperationType.MOVE)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"x\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[0], o.getEnd()[0]);
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"y\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[1], o.getEnd()[1]);
          } else if (o.getType().equals(OperationType.SCALE)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"width\" from=\"%.1f\" to=\"%.1f\" " +
                            "fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[0], o.getEnd()[0]);
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"height\" from=\"%.1f\" to=\"%.1f\" " +
                            "fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[1], o.getEnd()[1]);
          } else if (o.getType().equals(OperationType.CHANGECOLOR)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"fill\" from=\"rgb(%.1f,%.1f,%.1f)\" " +
                            "to=\"rgb(%.1f,%.1f,%.1f)\" fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[0],
                    o.getStart()[1], o.getStart()[2], o.getEnd()[0], o.getEnd()[1], o.getEnd()[2]);
          }
        }

        pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%fs\" " +
                        "attributeName=\"width\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT2() / speed, 0.1, 0.0);
        pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%fs\" " +
                        "attributeName=\"height\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT2() / speed, 0.1, 0.0);
        pout.println("</rect>\n");
      } else if (s.getType().equals(ShapeType.OVAL)) {
        pout.printf("<ellipse id=\"%s\" cx=\"%.1f\" cy=\"%.1f\" rx=\"%.1f\" ry=\"%.1f\" " +
                        "fill=\"rgb(%d,%d,%d)\" visibility=\"visible\" >\n",
                s.getName(), s.getPos()[0], s.getPos()[1], 0.0, 0.0,
                (int) (s.getCol()[0]*255), (int) (s.getCol()[1]*255), (int) (s.getCol()[2]*255));
        pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%fs\" " +
                        "attributeName=\"rx\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT1() / speed, 0.1, s.getScl()[0]);
        pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%fs\" " +
                        "attributeName=\"ry\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT1() / speed, 0.1, s.getScl()[1]);

        for (Operation o : model.getAni().get(s)) {
          if (o.getType().equals(OperationType.MOVE)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"cx\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[0], o.getEnd()[0]);
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"cy\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[1], o.getEnd()[1]);
          } else if (o.getType().equals(OperationType.SCALE)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"rx\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[0], o.getEnd()[0]);
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"ry\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[1], o.getEnd()[1]);
          } else if (o.getType().equals(OperationType.CHANGECOLOR)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%ds\" " +
                            "attributeName=\"fill\" from=\"rgb(%.1f,%.1f,%.1f)\" " +
                            "to=\"rgb(%.1f,%.1f,%.1f)\" fill=\"freeze\" />\n",
                    o.getT1() / speed, (o.getT2() - o.getT1()) / speed, o.getStart()[0],
                    o.getStart()[1], o.getStart()[2], o.getEnd()[0], o.getEnd()[1], o.getEnd()[2]);
          }
        }
        pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%fs\" " +
                        "attributeName=\"rx\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT2() / speed, 0.1, 0.0);
        pout.printf("<animate attributeType=\"xml\" begin=\"%ds\" dur=\"%fs\" " +
                        "attributeName=\"ry\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT2() / speed, 0.1, 0.0);
        pout.println("</ellipse>\n");
      }
    }
    pout.println("</svg>\n");
  }
}