import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test public methods in DataImpl Class.
 */
public class DataImplTest {
  // Use to test kmeans
  private DataImpl di1;
  private DataImpl di2;
  // Use to test fitLine
  private DataImpl di3;
  private DataImpl di4;
  // Use to test addPoint, getPoints, initializePoints
  private DataImpl di5;

  /**
   * Initialize 4 DataImpl objects used to test.
   */
  @Before
  public void setUp() {
    di1 = new DataImpl();
    di2 = new DataImpl();
    di3 = new DataImpl();
    di4 = new DataImpl();
    di5 = new DataImpl();
    di1.initializePoints("./data/clusterdata-3.txt");
    di2.initializePoints("./data/clusterdata-6.txt");
    di3.initializePoints("./data/linedata-1.txt");
    di4.initializePoints("./data/linedata-2.txt");
    di5.initializePoints("./data/self-data.txt");
  }

  /**
   * Test fitLine() accuracy using linedata-1.txt.
   */
  @Test
  public void testFitLineFor1() {
    double[] res = di3.fitLine();
    // randomly chosen points index in linedata-1.txt we used to test
    // 13, 91, 171, 250, 333, 393
    // calculate by my hand using the formula we get
    double k = 0.81603542;
    double b = 50.84817741;

    assertEquals(k, res[0], 0.3);
    assertEquals(b, res[1], 0.3);
  }

  /**
   * Test fitLine() accuracy using linedata-2.txt.
   */
  @Test
  public void testFitLineFor2() {
    double[] res = di4.fitLine();
    // randomly chosen points index in linedata-2.txt we used to test
    // 5, 29, 75, 131, 192, 240, 306, 371, 378
    // calculate by my hand using the formula we get
    double k = -0.46922190;
    double b = 104.62162923;

    assertEquals(k, res[0], 0.1);
    assertEquals(b, res[1], 5);
  }

  /**
   * Test kmeans(int k) accuracy using clusterdata-3.txt.
   */
  @Test
  public void testKmeansFor3() {
    List<Integer> pointCategories = di1.kmeans(3);
    // points index in clusterdata-3.txt we used to test
    // 26, 89, 101, 160, 400, 494
    assertTrue(pointCategories.get(26) == pointCategories.get(89));
    assertTrue(pointCategories.get(26) != pointCategories.get(101));
    assertTrue(pointCategories.get(26) != pointCategories.get(160));
    assertTrue(pointCategories.get(101) == pointCategories.get(400));
    assertTrue(pointCategories.get(160) == pointCategories.get(494));
  }

  /**
   * Test kmeans(int k) accuracy using clusterdata-6.txt.
   */
  @Test
  public void testKmeansFor6() {
    List<Integer> pointCategories = di2.kmeans(6);
    // points index in clusterdata-6.txt we used to test
    // 249, 250, 526, 569, 961, 1231, 1538, 1989
    assertTrue(pointCategories.get(1231) == pointCategories.get(961));
    assertTrue(pointCategories.get(1231) != pointCategories.get(1989));
    assertTrue(pointCategories.get(1231) != pointCategories.get(250));
    assertTrue(pointCategories.get(1989) != pointCategories.get(526));
    assertTrue(pointCategories.get(249) == pointCategories.get(569));
    assertTrue(pointCategories.get(1538) != pointCategories.get(961));
  }

  /**
   * Test kmeans(int k) with k equals 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalKmeans1() {
    List<Integer> pointCategories = di2.kmeans(0);
  }

  /**
   * Test kmeans(int k) with k below 0.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalKmeans2() {
    List<Integer> pointCategories = di2.kmeans(-3);
  }

  /**
   * Test the toString() method.
   */
  @Test
  public void testToString() {
    assertEquals("-245.29,75.08; 180.85,-112.71;", di5.toString());
    DataImpl di = new DataImpl();
    assertEquals("", di.toString());
  }

  /**
   * Test addPoint(double x, double y) method.
   */
  @Test
  public void testAddPoint() {
    di5.addPoint(23.12, -5.34);
    assertEquals("-245.29,75.08; 180.85,-112.71; 23.12,-5.34;", di5.toString());
    DataImpl di = new DataImpl();
    di.addPoint(180.85, -112.71);
    assertEquals("180.85,-112.71;", di.toString());
  }

  /**
   * Test getPoints() method.
   */
  @Test
  public void testGetPoints() {
    DataImpl di = new DataImpl();
    List<Point> points = di5.getPoints();
    for (Point p : points) {
      di.addPoint(p.getX(), p.getY());
    }
    assertEquals(di.toString(), di5.toString());
  }

  /**
   * Test initializePoints(String fileSource) method.
   */
  @Test
  public void testInitializePoints() {
    DataImpl di = new DataImpl();
    di.addPoint(-245.29, 75.08);
    di.addPoint(180.85, -112.71);
    assertEquals(di.toString(), di5.toString());
  }
}