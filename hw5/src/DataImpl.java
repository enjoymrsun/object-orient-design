import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
   */
  public List<Integer> kmeans(int k) {
    return KmeansCluster.kmeansC(points, k);
  }

//  public KmeansClusterReturn kmeansTest(int k) {
//    return KmeansCluster.kmeansC(points, k);
//  }

  // sum of distance for all points in a cluster to the cluster's center
//  public double clusterCenterDistance(KmeansClusterReturn kCR, int clusterIdx) {
//    Point center = kCR.getClustersCenters()[clusterIdx];
//    double totalDis = 0;
//    for (int category : kCR.getEachPointBelongedClusters()) {
//      if (category == clusterIdx) {
//        totalDis = KmeansCluster.distance(center, )
//      }
//    }
//
//
//
//
//  }

  /**
   * Round a double to its nearest integer value. 3.44 -> 3. 4.67 -> 5. 3.49 -> 3.
   *
   * @param num the double number need to be rounded
   * @return integer result after round operation
   */
  private static int round(double num) {
    int numFloor = (int) num;
    int numCeil = numFloor + 1;
    return (num - numFloor < numCeil - num) ? numFloor : numCeil;
  }

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

  public static void main(String[] args) {
    try {
      Scanner sc = new Scanner(new FileInputStream("./data/linedata-1.txt"));
      DataImpl di = new DataImpl();
      while (sc.hasNextLine()) {
        String[] str = sc.nextLine().split(" ");
        di.addPoint(Double.parseDouble(str[0]), Double.parseDouble(str[1]));
      }

      ImagePlotter plotter = new ImagePlotter();
      plotter.setWidth(900);
      plotter.setHeight(500);

      plotter.setDimensions(-450, 450, -100, 400);
      for (Point p : di.getPoints()) {
        plotter.addPoint(round(p.getX()), round(p.getY()));
      }
      double[] params = di.fitLine();
      plotter.addLine(-400, round(-400 * params[0] + params[1]), 400, round(400 * params[0] + params[1]));

      try {
        plotter.write("linear-1.png");
      } catch (IOException e) {
        System.out.println(e.getMessage());
      }
    } catch (FileNotFoundException e) {
      System.out.println("File Not Find!");
    }
//    try {
//      Scanner sc = new Scanner(new FileInputStream("./data/linedata-2.txt"));
//      DataImpl di = new DataImpl();
//      while (sc.hasNextLine()) {
//        String[] str = sc.nextLine().split(" ");
//        di.addPoint(Double.parseDouble(str[0]), Double.parseDouble(str[1]));
//      }
//
//      ImagePlotter plotter = new ImagePlotter();
//      plotter.setWidth(900);
//      plotter.setHeight(500);
//
//      plotter.setDimensions(-450, 450, -100, 400);
//      for (Point p : di.getPoints()) {
//        plotter.addPoint(round(p.getX()), round(p.getY()));
//      }
//      double[] params = di.fitLine();
//      plotter.addLine(-400, round(-400 * params[0] + params[1]), 400, round(400 * params[0] + params[1]));
//
//      try {
//        plotter.write("linear-2.png");
//      } catch (IOException e) {
//        System.out.println(e.getMessage());
//      }
//    } catch (FileNotFoundException e) {
//      System.out.println("File Not Find!");
//    }
//    try {
//      Scanner sc = new Scanner(new FileInputStream("./data/linedata-3.txt"));
//      DataImpl di = new DataImpl();
//      while (sc.hasNextLine()) {
//        String[] str = sc.nextLine().split(" ");
//        di.addPoint(Double.parseDouble(str[0]), Double.parseDouble(str[1]));
//      }
//
//      ImagePlotter plotter = new ImagePlotter();
//      plotter.setWidth(900);
//      plotter.setHeight(300);
//
//      plotter.setDimensions(-450, 450, -50, 250);
//      for (Point p : di.getPoints()) {
//        plotter.addPoint(round(p.getX()), round(p.getY()));
//      }
//      double[] params = di.fitLine();
//      plotter.addLine(-400, round(-400 * params[0] + params[1]), 400, round(400 * params[0] + params[1]));
//
//      try {
//        plotter.write("linear-3.png");
//      } catch (IOException e) {
//        System.out.println(e.getMessage());
//      }
//    } catch (FileNotFoundException e) {
//      System.out.println("File Not Find!");
//    }

//    try {
//      Scanner sc = new Scanner(new FileInputStream("./data/clusterdata-2.txt"));
//      DataImpl di = new DataImpl();
//      while (sc.hasNextLine()) {
//        String[] str = sc.nextLine().split(" ");
//        di.addPoint(Double.parseDouble(str[0]), Double.parseDouble(str[1]));
//      }
//
//      ImagePlotter plotter = new ImagePlotter();
//      plotter.setWidth(470);
//      plotter.setHeight(600);
//
//      plotter.setDimensions(-50, 420, -450, 150);
//      List<Point> points = di.getPoints();
//      List<Integer> eachPointCluster = di.kmeans(2);
//      int[] arr = new int[points.size()];
//      for (int i = 0; i < arr.length; i++) {
//        arr[i] = eachPointCluster.get(i);
//      }
//      for (int i = 0; i < points.size(); i++) {
//        Point p = points.get(i);
//        if (arr[i] == 0) {
//          plotter.addPoint(round(p.getX()), round(p.getY()), Color.MAGENTA);
//        } else if (arr[i] == 1) {
//          plotter.addPoint(round(p.getX()), round(p.getY()), Color.GREEN);
//        } else if (arr[i] == 2) {
//          plotter.addPoint(round(p.getX()), round(p.getY()), Color.BLACK);
//        } else if (arr[i] == 3) {
//          plotter.addPoint(round(p.getX()), round(p.getY()), Color.BLUE);
//        } else if (arr[i] == 4) {
//          plotter.addPoint(round(p.getX()), round(p.getY()), Color.red);
//        } else if (arr[i] == 5) {
//          plotter.addPoint(round(p.getX()), round(p.getY()), Color.CYAN);
//        }
//      }
//
//      try {
//        plotter.write("cluster-2.png");
//      } catch (IOException e) {
//        System.out.println(e.getMessage());
//      }
//    } catch (FileNotFoundException e) {
//      System.out.println("File Not Find!");
//    }
  }
}
