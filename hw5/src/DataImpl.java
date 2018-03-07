import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This Class implements all methods related to stores and operations of points data.
 */
public class DataImpl {
  private List<Point> points;

  /**
   * Default constructor to create a DataImpl object with list of points initialized.
   */
  public DataImpl() {
    points = new ArrayList<>();
  }

  /**
   * Add a point object to this DataImpl object's list of points.
   *
   * @param x point's x coordinate
   * @param y point's y coordinate
   */
  public void addPoint(double x, double y) {
    points.add(new Point(x, y));
  }

  /**
   * Returns the data point entered thus far, as a list.
   *
   * @return the data points as a list
   */
  public List<Point> getPoints() {
    return new ArrayList<>(points);
  }

  /**
   * Returns a best-fit line with an array format: y = kx + b. result[0] is k. result[1] is b.
   *
   * @return the k and b parameter of the best-fit line
   */
  public double[] fitLine() {
    return LinearRegr.fitOneLine(points);
  }

  /**
   * Returns a list of integers, which represent each data point's belonged clusters.
   *
   * @param k number of clusters that need to divide these data points into
   * @return a list of integer represent each data point's belonged cluster
   * @throws IllegalArgumentException if k is equal or below 0
   */
  public List<Integer> kmeans(int k) throws IllegalArgumentException {
    if (k <= 0) {
      throw new IllegalArgumentException("k should be greater than 0.");
    }

    return KmeansCluster.kmeansC(points, k);
  }

  /**
   * Given a source file directory, we add all points in the source file to this DataImpl object.
   *
   * @param fileSource the file source directory in string format
   */
  public void initializePoints(String fileSource) {
    try {
      Scanner sc = new Scanner(new FileInputStream(fileSource));
      while (sc.hasNextLine()) {
        String[] str = sc.nextLine().split(" ");
        addPoint(Double.parseDouble(str[0]), Double.parseDouble(str[1]));
      }
    } catch (FileNotFoundException e) {
      System.out.println("No Such File Found For: " + fileSource);
    }
  }

  /**
   * Return the String format of this DataImpl object.
   *
   * @return string format of this DataImpl object
   */
  @Override
  public String toString() {
    if (points.size() == 0) {
      return "";
    }

    StringBuilder sb = new StringBuilder();

    for (Point p : points) {
      sb.append(p.toString());
    }
    int length = sb.length();

    return sb.deleteCharAt(length - 1).toString();
  }
}
