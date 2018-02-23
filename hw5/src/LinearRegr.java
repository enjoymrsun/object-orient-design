import java.util.List;

/**
 * This class provides a public method to generate a line that represent the data pattern with other
 * helper private method.
 */
public class LinearRegr {
  private static double PI = Math.PI;

  /**
   * Returns a best-fit line with an array format: y = kx + b. result[0] is k. result[1] is b.
   *
   * @param points all points that used to generate a line
   * @return a double array contains "y = kx + b"'s k and b
   * @throws IllegalArgumentException if both ft for theta and theta + 180 are negative, throw an
   *                                  exception
   */
  public static double[] fitOneLine(List<Point> points) throws IllegalArgumentException {
    double[] res = new double[2];
    int size = points.size();

    double xAvg = sumTypeOne(points, true) / (double) size;
    double yAvg = sumTypeOne(points, false) / (double) size;
    double Sxx = sumTypeTwo(points, 1, xAvg, yAvg);
    double Syy = sumTypeTwo(points, 2, xAvg, yAvg);
    double Sxy = sumTypeTwo(points, 3, xAvg, yAvg);
    double d = 2.0 * Sxy / (Sxx - Syy);
    double theta = Math.atan(d);
    double ft1 = ft(Sxx, Syy, Sxy, theta);
    double ft2 = ft(Sxx, Syy, Sxy, theta + PI);
    double t = (ft1 > 0) ? theta : ((ft2 > 0) ? theta + PI : -99999999);

    if (t == -99999999) {
      throw new IllegalArgumentException("Cannot generate valid linear regression result!");
    }
    double a = Math.cos(t / 2);
    double b = Math.sin(t / 2);
    double c = -a * xAvg - b * yAvg;
    // ax + by + c = 0 => y = k * x + b

    double k = -a / b;
    b = -c / b;
    res[0] = k;
    res[1] = b;

    return res;
  }

  /**
   * Sum all data points' x coordinate or y coordinate.
   *
   * @param points all data points we have
   * @param sumX   sum x coordinate or y coordinate
   * @return the sum of x or y coordinate
   */
  private static double sumTypeOne(List<Point> points, boolean sumX) {
    double sum = 0;
    if (sumX) {
      for (Point p : points) {
        sum += p.getX();
      }
    } else {
      for (Point p : points) {
        sum += p.getY();
      }
    }
    return sum;
  }

  /**
   * Calculate and return the result of Sxx, Syy or Sxy.
   *
   * @param points  all data points that used to calculate
   * @param sumType calculate Sxx (1), Syy (2) or Sxy (3)
   * @param xAvg    x coordinate average of all points
   * @param yAvg    y coordinate average of all points
   * @return Sxx, Syy or Sxy
   */
  private static double sumTypeTwo(List<Point> points, int sumType, double xAvg, double yAvg) {
    double sum = 0;

    if (sumType == 1) {
      // Sxx
      for (Point p : points) {
        sum += Math.pow(p.getX() - xAvg, 2);
      }
    } else if (sumType == 2) {
      // Syy
      for (Point p : points) {
        sum += Math.pow(p.getY() - yAvg, 2);
      }
    } else if (sumType == 3) {
      // Sxy
      for (Point p : points) {
        sum += (p.getX() - xAvg) * (p.getY() - yAvg);
      }
    }

    return sum;
  }

  /**
   * Return the ft result with the Sxx, Syy, Sxy and t.
   *
   * @param Sxx Sxx input value
   * @param Syy Syy input value
   * @param Sxy Sxy input value
   * @param t   theta in radiant format
   * @return the ft result
   */
  private static double ft(double Sxx, double Syy, double Sxy, double t) {
    return (Syy - Sxx) * Math.cos(t) - 2 * Sxy * Math.sin(t);
  }

}
