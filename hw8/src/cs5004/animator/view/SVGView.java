package cs5004.animator.view;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import cs5004.animator.AnimationImpl;
import cs5004.animator.Operation;
import cs5004.animator.OperationType;
import cs5004.animator.Shape;
import cs5004.animator.ShapeType;

/**
 * The is the SVG view class.
 */
public class SVGView implements ISVGView {
  private AnimationImpl model;

  /**
   * Construct a textual view.
   *
   * @param model the animation model
   */
  public SVGView(AnimationImpl model) {
    this.model = model;
  }

  @Override
  public void createSVG() throws IOException {
    FileOutputStream fout = new FileOutputStream("SVG-view.svg");
    PrintStream pout = new PrintStream(fout);

    pout.println("<svg width=\"700\" height=\"500\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (Shape s : model.getAni().keySet()) {
      if (s.getType().equals(ShapeType.RECTANGLE)) {
        pout.printf("<rect id=\"%c\" x=\"%.1f\" y=\"%.1f\" width=\"%.1f\" height=\"%.1f\" " +
                        "fill=\"rgb(%.1f,%.1f,%.1f)\" visibility=\"visible\" >\n",
                s.getName(), s.getPos()[0], s.getPos()[1], 0.0, 0.0,
                s.getCol()[0], s.getCol()[1], s.getCol()[2]);
        pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" " +
                        "attributeName=\"width\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT1(), 0.1, s.getScl()[0]);
        pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                        "attributeName=\"height\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT1(), 0.1, s.getScl()[1]);

        for (Operation o : model.getAni().get(s)) {
          if (o.getType().equals(OperationType.MOVE)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                            "attributeName=\"x\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[0], o.getEnd()[0]);
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                            "attributeName=\"y\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[1], o.getEnd()[1]);
          } else if (o.getType().equals(OperationType.SCALE)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" " +
                            "attributeName=\"width\" from=\"%.1f\" to=\"%.1f\" " +
                            "fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[0], o.getEnd()[0]);
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                            "attributeName=\"height\" from=\"%.1f\" to=\"%.1f\" " +
                            "fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[1], o.getEnd()[1]);
          } else if (o.getType().equals(OperationType.CHANGECOLOR)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                            "attributeName=\"fill\" from=\"rgb(%.1f,%.1f,%.1f)\" " +
                            "to=\"rgb(%.1f,%.1f,%.1f)\" fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[0], o.getStart()[1],
                    o.getStart()[2], o.getEnd()[0], o.getEnd()[1], o.getEnd()[2]);
          }
        }

        pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" " +
                        "attributeName=\"width\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT2(), 0.1, 0.0);
        pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                        "attributeName=\"height\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT2(), 0.1, 0.0);
        pout.println("</rect>\n");
      }

      else if (s.getType().equals(ShapeType.OVAL)) {
        pout.printf("<ellipse id=\"%c\" cx=\"%.1f\" cy=\"%.1f\" rx=\"%.1f\" ry=\"%.1f\" " +
                        "fill=\"rgb(%.1f,%.1f,%.1f)\" visibility=\"visible\" >\n",
                s.getName(), s.getPos()[0], s.getPos()[1], 0.0, 0.0,
                s.getCol()[0], s.getCol()[1], s.getCol()[2]);
        pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" " +
                        "attributeName=\"rx\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT1(), 0.1, s.getScl()[0]);
        pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                        "attributeName=\"ry\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT1(), 0.1, s.getScl()[1]);

        for (Operation o : model.getAni().get(s)) {
          if (o.getType().equals(OperationType.MOVE)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                            "attributeName=\"cx\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[0], o.getEnd()[0]);
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                            "attributeName=\"cy\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[1], o.getEnd()[1]);
          } else if (o.getType().equals(OperationType.SCALE)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" " +
                            "attributeName=\"rx\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[0], o.getEnd()[0]);
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                            "attributeName=\"ry\" from=\"%.1f\" to=\"%.1f\" fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[1], o.getEnd()[1]);
          } else if (o.getType().equals(OperationType.CHANGECOLOR)) {
            pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                            "attributeName=\"fill\" from=\"rgb(%.1f,%.1f,%.1f)\" " +
                            "to=\"rgb(%.1f,%.1f,%.1f)\" fill=\"freeze\" />\n",
                    o.getT1(), o.getT2() - o.getT1(), o.getStart()[0], o.getStart()[1],
                    o.getStart()[2], o.getEnd()[0], o.getEnd()[1], o.getEnd()[2]);
          }
        }
        pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\" " +
                        "attributeName=\"rx\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT2(), 0.1, 0.0);
        pout.printf("<animate attributeType=\"xml\" begin=\"%dms\" dur=\"%dms\"" +
                        "attributeName=\"ry\" to=\"%.1f\" fill=\"freeze\" />\n",
                s.getT2(), 0.1, 0.0);
        pout.println("</ellipse>\n");
      }
    }
    pout.println("</svg>\n");
  }
}