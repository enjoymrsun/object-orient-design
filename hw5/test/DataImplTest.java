import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DataImplTest {
  // Use to test kmeans
  private DataImpl di1;
  private DataImpl di2;
  // Use to test fitLine
  private DataImpl di3;
  private DataImpl di4;

  @Before
  public void setUp() {
    di1 = new DataImpl();
    di2 = new DataImpl();
    di3 = new DataImpl();
    di4 = new DataImpl();
    di1.initializePoints("./data/clusterdata-3.txt");
    di2.initializePoints("./data/clusterdata-6.txt");
    di3.initializePoints("./data/linedata-1.txt");
    di4.initializePoints("./data/linedata-2.txt");
  }

  @Test
  public void testFitLineFor1() {
    double[] res = di3.fitLine();
    DataImpl di5 = new DataImpl();
    // points index in linedata-1.txt we used to test
    // 13, 91, 171, 250, 333, 393
    // calculate by hand using the formula we get
    double k = 0.81603542;
    double b = 50.84817741;

    assertEquals(k, res[0], 0.3);
    assertEquals(b, res[1], 0.3);

//    double MSE = 0;
//    for (Point p : di3.getPoints()) {
//      double newX = p.getX();
//      double newY = newX * res[0] + res[1];
//      MSE += Math.pow(p.getY() - newY, 2);
//    }
//    MSE /= di3.getPoints().size();
//    assertEquals(10, MSE, 10);
//    di5.addPoint(-368.42, -248.89);
//    di5.addPoint(-207.87, -135.26);
//    di5.addPoint(-60.17, 15.49);
//    di5.addPoint(86.11, 136.70);
//    di5.addPoint(261.28, 253.45);
//    di5.addPoint(394.52, 369.65);
//    double[] resNew = di5.fitLine();
//    System.out.println("k是: " + resNew[0] + ", b是: " + resNew[1] + ".");
  }

  @Test
  public void testFitLineFor2() {
    double[] res = di4.fitLine();
    DataImpl di5 = new DataImpl();
    // points index in linedata-2.txt we used to test
    // 5, 29, 75, 131, 192, 240, 306, 371, 378
    // calculate by hand using the formula we get
    double k = -0.46922190;
    double b = 104.62162923;

    assertEquals(k, res[0], 0.1);
    assertEquals(b, res[1], 5);

//    5, 29, 75, 131, 192, 240, 306, 371, 378
//    di5.addPoint(-387.82, 270.79);
//    di5.addPoint(-336.81, 241.06);
//    di5.addPoint(-240.65, 250.03);
//    di5.addPoint(-118.69, 164.01);
//    di5.addPoint(9.75, 122.84);
//    di5.addPoint(108.50, 41.38);
//    di5.addPoint(261.86, -22.53);
//    di5.addPoint(388.73, -79.59);
//    di5.addPoint(396.53, -84.59);
//    double[] resNew = di5.fitLine();
//    System.out.println("k是: " + resNew[0] + ", b是: " + resNew[1] + ".");
  }

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

  @Test
  public void testKmeansFor6() {
    List<Integer> pointCategories = di2.kmeans(6);
    // points index in clusterdata-6.txt we used to test
    // 250, 526, 961, 1231, 1538, 1989
    assertTrue(pointCategories.get(1231) == pointCategories.get(961));
    assertTrue(pointCategories.get(1231) != pointCategories.get(1989));
    assertTrue(pointCategories.get(1231) != pointCategories.get(250));
    assertTrue(pointCategories.get(1989) != pointCategories.get(526));
    assertTrue(pointCategories.get(250) == pointCategories.get(526));
    assertTrue(pointCategories.get(1538) != pointCategories.get(961));
  }

}