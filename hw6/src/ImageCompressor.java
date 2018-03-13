import java.io.IOException;
import java.util.List;

/**
 * This class takes the path of the input image file and options as command-line arguments.
 */
public class ImageCompressor {

  /**
   * The main method can be used in terminal.
   *
   * @param args arguments for the main method to generate images
   * @throws IOException when file has something wrong
   */
  public static void main(String[] args) throws IOException {
    for (int i = 0; i < args.length; i++) {
      System.out.println("No." + i + " argument is: " + args[i]);
    }

    if (args.length >= 8 || args.length < 2) {
      System.err.println("You have too many or too little arguments!");
      System.exit(-1);
    }

    String outputFile = "out.png";
    String inputFile = null;
    String fileName = "";
    boolean needProgressive = false;
    boolean needCompress = false;
    float ratio = -0.1F;
    Image image = null;
    Image compressedImage = null;
    int j;
    int i = 0;

    while (i < args.length && args[i].startsWith("-")) {
      if (args[i].equals("-compress")) {
        needCompress = true;
        try {
          ratio = Float.parseFloat(args[++i]) / 100;
        } catch (NumberFormatException e) {
          System.err.println("Your Compression Ratio is incorrect!");
          System.exit(-1);
        }
      } else if (args[i].equals("-o")) {
        outputFile = args[++i];
      } else if (args[i].equals("-i")) {
        inputFile = args[++i];
      } else if (args[i].equals("-progressive")) {
        needProgressive = true;

        if ((i + 1) < args.length && !(args[i + 1]).startsWith("-")) {
          System.err.println("Cannot have a value after -progressive flag!");
          System.exit(-1);
        }
      } else {
        System.err.println("ParseCmdLine: illegal option, software cannot understand.");
        System.exit(-1);
      }
      ++i;
    }

    if (inputFile == null) {
      System.err.println("Must have an input file directory.");
      System.exit(-1);
    }

    try {
      int[][][] colors = ImageUtil.readImage(inputFile);
      image = new Image(colors, ImageUtil.getWidth(inputFile), ImageUtil
              .getHeight(inputFile));
    } catch (IOException e) {
      System.err.println("Get an input Image results an error!");
      System.exit(-1);
    }

    if ((ratio < 0 || ratio > 1) && needCompress) {
      System.err.println("Ratio is in wrong range.");
      System.exit(-1);
    }

    if (ratio != -0.1F && needCompress) {
      compressedImage = image.compress(ratio);

      try {
        ImageUtil.writeImage(compressedImage.toArray(), compressedImage.getWidth(),
                compressedImage.getHeight(), outputFile);
      } catch (IOException e) {
        System.err.println("Write into a compressed Image results an error!");
        System.exit(-1);
      } finally {
        System.out.println("Compressed Image Created Successfully!");
      }
    }

    fileName = inputFile.substring(0, inputFile.lastIndexOf("."));

    if (needProgressive) {
      List<Image> list = image.progressive();
      for (j = 0; j < list.size(); ++j) {
        int k = list.size() - 1 - j;
        String outputImageName = fileName + "-" + k + ".png";
        try {
          ImageUtil.writeImage(list.get(j).toArray(), list.get(j).getWidth(),
                  list.get(j).getHeight(), outputImageName);
        } catch (IOException e) {
          System.err.println("Write a progressive Image results an error!");
          System.exit(-1);
        }
      }
      System.out.println("Progressive Images Created Successfully!");
    }
  }
}
