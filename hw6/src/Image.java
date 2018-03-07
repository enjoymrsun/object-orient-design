import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Image {
  private int originalWidth;
  private int originalHeight;
  private int[][][] pixels;

  public Image(int[][][] array, int width, int height) throws IllegalArgumentException {
    if (array == null || array.length == 0 || array[0].length == 0 || array[0][0].length == 0 ||
            width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid Input.");
    }

    this.pixels = array;
    this.originalWidth = width;
    this.originalHeight = height;
  }

  public Image(int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid Input.");
    }

    this.originalWidth = width;
    this.originalHeight = height;

    this.pixels = new int[height][width][3];
  }

  public int getWidth() {
    return this.originalWidth;
  }

  public int getHeight() {
    return this.originalHeight;
  }

  // 根据Image大小而来，所以只截取到 originalHeight 和 originalWidth 的 pixels
  public int[][][] toArray() {
    int[][][] newPixels = new int[originalHeight][originalWidth][3];
    for (int row = 0; row < originalHeight; row++) {
      for (int column = 0; column < originalWidth; column++) {
        for (int channel = 0; channel < 3; channel++) {
          newPixels[row][column][channel] = pixels[row][column][channel];
        }
      }
    }

    return newPixels;
  }

  private int round(double val) {
    double leftDiff = val - ((int) val);
    double rightDiff = ((int) val) + 1 - val;
    return leftDiff < rightDiff ? ((int) val) : ((int) val) + 1;
  }

  private void normalize() {
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int height = pixels.length;
    int width = pixels[0].length;

    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        for (int channel = 0; channel < 3; channel++) {
          max = Math.max(max, pixels[row][column][channel]);
          min = Math.min(min, pixels[row][column][channel]);
        }
      }
    }

    for (int row = 0; row < height; row++) {
      for (int column = 0; column < width; column++) {
        for (int channel = 0; channel < 3; channel++) {
          double v = pixels[row][column][channel];
          v = (v - min) * 255.0 / (max - min);
          pixels[row][column][channel] = round(v);
        }
      }
    }
  }

  private int squareLength(int maxCurr) {
    if (maxCurr == 1 || maxCurr == 2) {
      return maxCurr;
    }

    int squareLength = 1;
    while (squareLength * 2 < maxCurr) {
      squareLength *= 2;
    }
    return squareLength * 2;
  }

  private double[] oneDimHaarWaveletTransform(double[] oneDimPixels) {
    int size = oneDimPixels.length;
    List<Double> newPixelsList = null;
    while (size >= 2) {
      newPixelsList = new ArrayList<>(size);
      for (int i = 0; i <= size - 2; i += 2) {
        newPixelsList.add((oneDimPixels[i] + oneDimPixels[i + 1]) / Math.sqrt(2));
      }
      for (int i = 0; i <= size - 2; i += 2) {
        newPixelsList.add((oneDimPixels[i] - oneDimPixels[i + 1]) / Math.sqrt(2));
      }

      for (int i = 0; i < newPixelsList.size(); i++) {
        oneDimPixels[i] = newPixelsList.get(i);
      }
      size /= 2;
    }

    return oneDimPixels;
  }

  private double[][] transpose(double[][] pixels) {
    int length = pixels.length;
    double[][] resultPixels = new double[length][length];

    // TRANSPOSE 转置矩阵
    for (int column = 0; column < length; column++) {
      for (int row = 0; row < length; row++) {
        resultPixels[column][row] = pixels[row][column];
      }
    }

    return resultPixels;
  }

  private double[][] twoDimHaarWaveletTransform(double[][] twoDimPixels) {
    int rows = twoDimPixels.length;

    // make it to J
    for (int row = 0; row < rows; row++) {
      double[] resultRow = oneDimHaarWaveletTransform(twoDimPixels[row]);
      for (int i = 0; i < resultRow.length; i++) {
        twoDimPixels[row][i] = resultRow[i];
      }
    }

    // make it to K
    double[][] transposePixels = transpose(twoDimPixels);
    for (int row = 0; row < rows; row++) {
      double[] resultRow = oneDimHaarWaveletTransform(transposePixels[row]);
      for (int i = 0; i < resultRow.length; i++) {
        transposePixels[row][i] = resultRow[i];
      }
    }

    // transpose back
    return transpose(transposePixels);
  }

  private double threshold(float compressionRatio, List<Double> array) {
    int size = array.size();

    int pos = (int) (size * compressionRatio);

    Collections.sort(array);

    return array.get(pos);
  }

  private double[] oneDimInverseHaarWaveletTransform(double[] oneDimPixels) {
    int length = oneDimPixels.length;
    int size = 2;
    List<Double> newPixelsList = null;
    while (size <= length) {
      newPixelsList = new ArrayList<>(size);
      for (int i = 0; i < (size / 2); i++) {
        int j = i + (size / 2);
        newPixelsList.add((oneDimPixels[i] + oneDimPixels[j]) / Math.sqrt(2));
        newPixelsList.add((oneDimPixels[i] - oneDimPixels[j]) / Math.sqrt(2));
      }

      for (int i = 0; i < size; i++) {
        oneDimPixels[i] = newPixelsList.get(i);
      }
      size *= 2;
    }

    return oneDimPixels;
  }

  private double[][] twoDimInverseHaarWaveletTransform(double[][] twoDimPixels) {
    // FIRST TRANSPOSE array
    // first apply it to each column of K to get J
    double[][] transposePixels = transpose(twoDimPixels);
    int rows = twoDimPixels.length;

    // make it from K to J
    for (int row = 0; row < rows; row++) {
      double[] resultRow = oneDimInverseHaarWaveletTransform(transposePixels[row]);
      for (int i = 0; i < resultRow.length; i++) {
        transposePixels[row][i] = resultRow[i];
      }
    }

    twoDimPixels = transpose(transposePixels);

    // make it from J to I
    for (int row = 0; row < rows; row++) {
      double[] resultRow = oneDimInverseHaarWaveletTransform(twoDimPixels[row]);
      for (int i = 0; i < resultRow.length; i++) {
        twoDimPixels[row][i] = resultRow[i];
      }
    }

    return twoDimPixels;
  }

  public Image compress(float compressionRatio) {
    int squareLength = squareLength(Math.max(originalHeight, originalWidth));
    int[][][] newPixels = null;

    double[][] newPixelsRed = new double[squareLength][squareLength];
    double[][] newPixelsGreen = new double[squareLength][squareLength];
    double[][] newPixelsBlue = new double[squareLength][squareLength];

    int currHeight = pixels.length;
    int currWidth = pixels[0].length;

    for (int row = 0; row < currHeight; row++) {
      for (int column = 0; column < currWidth; column++) {
        newPixelsRed[row][column] = pixels[row][column][0];
        newPixelsGreen[row][column] = pixels[row][column][1];
        newPixelsBlue[row][column] = pixels[row][column][2];
      }
    }

    newPixelsRed = twoDimHaarWaveletTransform(newPixelsRed);
    newPixelsGreen = twoDimHaarWaveletTransform(newPixelsGreen);
    newPixelsBlue = twoDimHaarWaveletTransform(newPixelsBlue);

    List<Double> array = new ArrayList<>(squareLength * squareLength * 3);
    for (double[] row : newPixelsRed) {
      for (double num : row) {
        array.add(Math.abs(num));
      }
    }
    for (double[] row : newPixelsGreen) {
      for (double num : row) {
        array.add(Math.abs(num));
      }
    }
    for (double[] row : newPixelsBlue) {
      for (double num : row) {
        array.add(Math.abs(num));
      }
    }

    double threshold = threshold(compressionRatio, array);

    for (int row = 0; row < squareLength; row++) {
      for (int column = 0; column < squareLength; column++) {
        if (Math.abs(newPixelsRed[row][column]) <= threshold) {
          newPixelsRed[row][column] = 0;
        }
        if (Math.abs(newPixelsGreen[row][column]) <= threshold) {
          newPixelsGreen[row][column] = 0;
        }
        if (Math.abs(newPixelsBlue[row][column]) <= threshold) {
          newPixelsBlue[row][column] = 0;
        }
      }
    }

    newPixelsRed = twoDimInverseHaarWaveletTransform(newPixelsRed);
    newPixelsGreen = twoDimInverseHaarWaveletTransform(newPixelsGreen);
    newPixelsBlue = twoDimInverseHaarWaveletTransform(newPixelsBlue);

    newPixels = new int[originalHeight][originalWidth][3];

    for (int row = 0; row < originalHeight; row++) {
      for (int column = 0; column < originalWidth; column++) {
        newPixels[row][column][0] = round(newPixelsRed[row][column]);
        newPixels[row][column][1] = round(newPixelsGreen[row][column]);
        newPixels[row][column][2] = round(newPixelsBlue[row][column]);
      }
    }

    return new Image(newPixels, originalWidth, originalHeight);
  }

  public List<Image> progressive() {
    List<Image> resultList = new ArrayList<>();
    for (int i = 0; i < 1; i += 0.1) {
      resultList.add(compress(i));
    }
    resultList.add(compress(1));

    return resultList;
  }
}
