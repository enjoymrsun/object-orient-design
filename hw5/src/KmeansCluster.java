import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a public method to do a k-means clustering for a list of data, with other
 * helper methods provided.
 */
public class KmeansCluster {
  /**
   * Returns a list of integer, which represent each data point's belonged clusters, by doing a
   * k-means clustering.
   *
   * @param points the data points need to do a k-means clustering.
   * @param k      k clusters that these data points need to be divided into
   * @return a list of integer represent each data point's belonged cluster
   */
  public static List<Integer> kmeansC(List<Point> points, int k) {
    double prevPercentageError = 1;
    List<Integer> res = new ArrayList<>();
//    Point[] resCenters = null;

    for (int t = 0; t < 10; t++) {
      Point[] centers = new Point[k];
      int[] positions = randomKCenters(points.size(), k);
      for (int i = 0; i < centers.length; i++) {
        centers[i] = points.get(positions[i]);
      }

      double e = Double.POSITIVE_INFINITY;
      int times = 0;

      int[] clusters = null;

      while (times < 100) {
        clusters = categorize(points, centers, k);

        double[] eachClusterXSum = eachClusterCoordinateSum(points, clusters, true, k);
        double[] eachClusterYSum = eachClusterCoordinateSum(points, clusters, false, k);
        Point[] newCenters = computeNewCenters(eachClusterXSum, eachClusterYSum, clusters, k);
        double ne = computeNewError(points, clusters, newCenters);

        if (Math.abs(ne - e) / e < 0.0001) {
          if (Math.abs(ne - e) / e < prevPercentageError) {
            prevPercentageError = Math.abs(ne - e) / e;
            List<Integer> tempRes = new ArrayList<>();
            for (int cluster : clusters) {
              tempRes.add(cluster);
            }
            res = tempRes;
            // resCenters = newCenters;
          }
          break;
        }
        e = ne;
        centers = newCenters;
        times++;
      }
    }

//    return new KmeansClusterReturn(res, resCenters);
    return res;
  }

  /**
   * Return the new error computed with the given list of data, with each data's belonged cluster
   * and each cluster's new center.
   *
   * @param points     a list of points need to calculate
   * @param clusters   each point's belonged cluster
   * @param newCenters each cluster's new center position
   * @return the new error
   */
  private static double computeNewError(List<Point> points, int[] clusters, Point[] newCenters) {
    double sum = 0;
    for (int i = 0; i < points.size(); i++) {
      Point p = points.get(0);
      sum += distance(p, newCenters[clusters[i]]);
    }

    return sum / points.size();
  }

  /**
   * Return the new center's position for each cluster with each cluster's all x coordinates sum,
   * all y coordinates sum and each data point's belonged cluster.
   *
   * @param eCXSum   each cluster's data points' x coordinates sum
   * @param eCYSum   each cluster's data points' y coordinates sum
   * @param clusters each data point's belonged cluster
   * @param k        k clusters need to divide these data points into
   * @return each cluster's new center position
   */
  private static Point[] computeNewCenters(double[] eCXSum, double[] eCYSum, int[] clusters, int k) {
    Point[] newCenters = new Point[k];

    int[] count = new int[k];
    for (int c : clusters) {
      count[c]++;
    }

    for (int i = 0; i < k; i++) {
      newCenters[i] = new Point(eCXSum[i] / count[i], eCYSum[i] / count[i]);
    }

    return newCenters;
  }

  /**
   * Return the coordinate(x or y) sum of each cluster's belonged data points.
   *
   * @param points   the list of data points that we need to know their positions.
   * @param clusters each data point's belonged cluster
   * @param sumX     whether you are summing x coordinate (true) or y coordinate (false)
   * @param k        number of clusters that these data points need to be divided into
   * @return the coordinate(x or y) sum of each cluster's belonged data points
   */
  private static double[] eachClusterCoordinateSum(List<Point> points, int[] clusters, boolean sumX, int k) {
    double[] eachClusterXorYSum = new double[k];
    if (sumX) {
      for (int i = 0; i < points.size(); i++) {
        Point p = points.get(i);
        eachClusterXorYSum[clusters[i]] += p.getX();
      }
    } else {
      for (int i = 0; i < points.size(); i++) {
        Point p = points.get(i);
        eachClusterXorYSum[clusters[i]] += p.getY();
      }
    }

    return eachClusterXorYSum;
  }

  /**
   * Return each data points' belonged cluster number.
   *
   * @param points  points that need to do categorize
   * @param centers a list of center need to be used for each data point to categorize
   * @param k       number of clusters that these points need to be divided into
   * @return each data point's belonged cluster
   */
  private static int[] categorize(List<Point> points, Point[] centers, int k) {
    // map each point to its belonged cluster
    int[] clusters = new int[points.size()];

    for (int i = 0; i < points.size(); i++) {
      Point p = points.get(i);
      double minDist = Double.POSITIVE_INFINITY;
      for (int j = 0; j < k; j++) {
        double dist = distance(p, centers[j]);
        if (dist < minDist) {
          minDist = dist;
          clusters[i] = j;
        }
      }
    }

    return clusters;
  }

  /**
   * Distance between two points.
   *
   * @param p1 point 1
   * @param p2 point 2
   * @return distance in double format
   */
  private static double distance(Point p1, Point p2) {
    double deltaXSquare = Math.pow(p1.getX() - p2.getX(), 2);
    double deltaYSquare = Math.pow(p1.getY() - p2.getY(), 2);

    return Math.sqrt(deltaXSquare + deltaYSquare);
  }

  /**
   * Return random center's index in the data points, initialized for start
   *
   * @param size total number of data points
   * @param k    k centers we need to initialized
   * @return random center's index in the data points
   */
  private static int[] randomKCenters(int size, int k) {
    boolean[] chosen = new boolean[size];
    int[] resPointPos = new int[k];
    int idx = 0;
    while (k > 0) {
      int pos = (int) (Math.random() * size);
      while (chosen[pos]) {
        pos = (pos + 1) % size;
      }
      resPointPos[idx++] = pos;
      chosen[pos] = true;
      k--;
    }

    return resPointPos;
  }
}
