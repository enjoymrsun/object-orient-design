import java.util.List;

public class KmeansClusterReturn {
  private List<Integer> eachPointBelongedClusters;
  private Point[] clustersCenters;


  public KmeansClusterReturn(List<Integer> eachPointBelongedClusters, Point[] clustersCenters) {
    this.eachPointBelongedClusters = eachPointBelongedClusters;
    this.clustersCenters = clustersCenters;
  }

  public List<Integer> getEachPointBelongedClusters() {
    return eachPointBelongedClusters;
  }

  public Point[] getClustersCenters() {
    return clustersCenters;
  }
}
