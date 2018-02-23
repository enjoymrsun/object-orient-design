public class Josephus {
  public static int whoCanLive(int peopleNum, int COUNT) {
    int count = COUNT;
    boolean[] killed = new boolean[peopleNum];
    int killedNum = 0;
    int idx = 0;
    while (killedNum < peopleNum - 1) {
      int pass = 0;
      for (; pass < count; idx = (idx + 1) % peopleNum) {
        if (!killed[idx]) {
          pass++;
        }
      }
      killedNum++;
      for (; killed[idx]; idx = (idx + 1) % peopleNum) {

      }
      killed[idx] = true;
      idx = (idx + 1) % peopleNum;
    }
    for (int i = 0; i < peopleNum; i++) {
      if (killed[i] == false) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    System.out.println("10 Poeple, Pass 4, Who can live: " + whoCanLive(10, 4));
    System.out.println("50 Poeple, Pass 2, Who can live: " + whoCanLive(50, 2));
    System.out.println("7 Poeple, Pass 4, Who can live: " + whoCanLive(7, 4));
    System.out.println("3 Poeple, Pass 1, Who can live: " + whoCanLive(3, 1));

    System.out.println("20 Poeple, Pass 4, Who can live: " + whoCanLive(20, 4));
    System.out.println("17 Poeple, Pass 7, Who can live: " + whoCanLive(17, 7));
    System.out.println("33 Poeple, Pass 11, Who can live: " + whoCanLive(33, 11));
  }
}
