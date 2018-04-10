package cs5004.animator.view;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cs5004.animator.AnimationFileReader;
import cs5004.animator.EasyAnimator;
import cs5004.animator.TweenModelBuilder;
import cs5004.animator.model.AnimationImpl;
import cs5004.animator.model.IAnimation;

import static org.junit.Assert.*;

/**
 * This class tests whether the methods on IView interface is correct or not.
 */
public class IViewTest {
  private IView svgView;
  private IView textualView;

  @Before
  public void setUp() throws Exception {
    IAnimation modelForSVG = null;
    IAnimation modelForTextual = null;
    String animationFile = "./operations/smalldemo.txt";

    try {
      TweenModelBuilder<IAnimation> builderSVG = new AnimationImpl.Builder();
      AnimationFileReader afrSVG = new AnimationFileReader();
      modelForSVG = afrSVG.readFile(animationFile, builderSVG);

      TweenModelBuilder<IAnimation> builderTextual = new AnimationImpl.Builder();
      AnimationFileReader afrTextual = new AnimationFileReader();
      modelForTextual = afrTextual.readFile(animationFile, builderTextual);
    } catch (IOException e) {
      System.err.println("File IO Error!");
      System.exit(-1);
    }

    svgView = new SVGView(modelForSVG);
    textualView = new TextualView(modelForTextual);
  }

  private String testDisplayHelper(String fileName) throws Exception {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    try {
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      return sb.toString();
    } finally {
      br.close();
    }
  }

  @Test
  public void testDisplay() throws Exception {
    String outputSVGFile = "./results/smalldemo-svg.svg";
    String outputTextualFile = "./results/smalldemo-textual.txt";

    svgView.display(outputSVGFile, 4);
    textualView.display(outputTextualFile, 5);

    String outputSVGContent = testDisplayHelper(outputSVGFile);
    String outputTextualContent = testDisplayHelper(outputTextualFile);

    assertEquals("<svg width=\"1800\" height=\"1500\" version=\"1.1\"\n" +
            "     xmlns=\"http://www.w3.org/2000/svg\">\n" +
            "\n" +
            "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"0.0\" " +
            "height=\"0.0\" fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"0s\" dur=\"0.100000s\" " +
            "attributeName=\"width\" to=\"25.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"0s\" dur=\"0.100000s\" " +
            "attributeName=\"height\" to=\"100.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2s\" dur=\"10s\" " +
            "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"2s\" dur=\"10s\" " +
            "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"17s\" dur=\"7s\" " +
            "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"17s\" dur=\"7s\" " +
            "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"12s\" dur=\"4s\" " +
            "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"12s\" dur=\"4s\" " +
            "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"25s\" dur=\"0.100000s\" " +
            "attributeName=\"width\" to=\"0.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"25s\" dur=\"0.100000s\" " +
            "attributeName=\"height\" to=\"0.0\" fill=\"freeze\" />\n" +
            "</rect>\n" +
            "\n" +
            "<ellipse id=\"C\" cx=\"500.0\" cy=\"400.0\" rx=\"0.0\" ry=\"0.0\" " +
            "fill=\"rgb(0,255,0)\" visibility=\"visible\" >\n" +
            "<animate attributeType=\"xml\" begin=\"1s\" dur=\"0.100000s\" " +
            "attributeName=\"rx\" to=\"60.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"1s\" dur=\"0.100000s\" " +
            "attributeName=\"ry\" to=\"30.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"5s\" dur=\"12s\" " +
            "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"5s\" dur=\"12s\" " +
            "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"12s\" dur=\"7s\" " +
            "attributeName=\"fill\" from=\"rgb(0.0,0.0,1.0)\" to=\"rgb(0.0,1.0,0.0)\" " +
            "fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"25s\" dur=\"0.100000s\" " +
            "attributeName=\"rx\" to=\"0.0\" fill=\"freeze\" />\n" +
            "<animate attributeType=\"xml\" begin=\"25s\" dur=\"0.100000s\" " +
            "attributeName=\"ry\" to=\"0.0\" fill=\"freeze\" />\n" +
            "</ellipse>\n" +
            "\n" +
            "</svg>\n" +
            "\n", outputSVGContent);

    assertEquals("Shapes\n" +
            "Name:R\n" +
            "Type:RECTANGLE\n" +
            "Min corner: (200.0,200.0), Width: 25.0, Height: 100.0, Color: (1.0,0.0,0.0)\n" +
            "Appears at t=0s\n" +
            "Disappears at t=20s\n" +
            "\n" +
            "Name:C\n" +
            "Type:OVAL\n" +
            "Center: (500.0,400.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,1.0,0.0)\n" +
            "Appears at t=1s\n" +
            "Disappears at t=20s\n" +
            "\n" +
            "Shape R moves from (200.0,200.0) to (300.0,300.0) from t=2s to t=10s\n" +
            "Shape C moves from (500.0,100.0) to (500.0,400.0) from t=4s to t=14s\n" +
            "Shape C changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=10s to t=16s\n" +
            "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, " +
            "Height: 100.0 from t=10s to t=14s\n" +
            "Shape R moves from (300.0,300.0) to (200.0,200.0) " +
            "from t=14s to t=20s\n", outputTextualContent);
  }

}