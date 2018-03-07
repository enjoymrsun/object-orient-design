import java.io.IOException;

/**
 * An example main method that "drives" this program
 */
public class ImageTrial {
  public static void main(String[] args) throws IOException {
    String filename = "images/earthmap.png";
    int[][][] colors = ImageUtil.readImage(filename);
    Image image = new Image(colors, ImageUtil.getWidth(filename), ImageUtil
            .getHeight(filename));

    ImageUtil.writeImage(image.toArray(), image.getWidth(), image.getHeight(),
            "images/earthmap-out.png");
  }
}
