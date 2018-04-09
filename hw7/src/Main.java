import java.util.Scanner;
import java.util.TreeMap;

public class Main {


  public static int[] helper(TreeMap<Integer, Integer> tree, int[] Ais) {
    int[] res = new int[Ais.length];
    for (int i = 0; i < Ais.length; i++) {
      Integer di = tree.floorKey(Ais[i]);
      if (di != null) {
        res[i] = tree.get(di);
      }
    }

    return res;
  }


  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int M = sc.nextInt();
    TreeMap<Integer, Integer> tree = new TreeMap<>();
    for (int i = 1; i <= N; i++) {
      int Di = sc.nextInt();
      int Pi = sc.nextInt();
      if (tree.get(Di) != null) {
        if (tree.get(Di) < Pi) {
          tree.put(Di, Pi);
        }
      } else {
        tree.put(Di, Pi);
      }
      Integer temp = Di;
      while ((temp = tree.higherKey(temp)) != null) {
        if (tree.get(temp) < Pi) {
          tree.put(temp, Pi);
        }
      }
    }
    int[] Ais = new int[M];
    for (int i = 0; i < M; i++) {
      Ais[i] = sc.nextInt();
    }
    int[] res = helper(tree, Ais);
    for (int i = 0; i < M; i++) {
      System.out.println(res[i]);
    }
  }
}
