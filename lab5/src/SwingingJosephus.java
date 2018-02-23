public class SwingingJosephus {
  public static int whoCanLive(int peopleNum, int COUNT) {
    int count = COUNT;
    boolean[] killed = new boolean[peopleNum];
    int killedNum = 0;
    int idx = 0;
    boolean posi = true;
    while (killedNum < peopleNum - 1) {
      int pass = 0;
      for (; pass < count; ) {
        if (!killed[idx]) {
          pass++;
        }
        idx = nextPos(peopleNum, idx, posi);
      }
      for (; killed[idx]; ) {
        idx = nextPos(peopleNum, idx, posi);
      }
      killedNum++;
      killed[idx] = true;
      posi = !posi;
      idx = nextPos(peopleNum, idx, posi);
    }
    for (int i = 0; i < peopleNum; i++) {
      if (killed[i] == false) {
        return i;
      }
    }
    return -1;
  }

  private static int nextPos(int peopleNum, int idx, boolean posi) {
    if (posi) {
      idx = (idx + 1) % peopleNum;
    } else {
      if (idx == 0) {
        idx = peopleNum - 1;
      } else {
        idx--;
      }
    }

    return idx;
  }

  public static void main(String[] args) {
    System.out.println("5 Poeple, Pass 3, Swinging, Who can live: " + whoCanLive(5, 3));
    System.out.println("100 Poeple, Pass 17, Swinging, Who can live: " + whoCanLive(100, 17));
    System.out.println("30 Poeple, Pass 7, Swinging, Who can live: " + whoCanLive(30, 7));
    System.out.println("12 Poeple, Pass 3, Swinging, Who can live: " + whoCanLive(12, 3));
    System.out.println("57 Poeple, Pass 45, Swinging, Who can live: " + whoCanLive(57, 45));
    System.out.println("17 Poeple, Pass 2, Swinging, Who can live: " + whoCanLive(17, 2));
    System.out.println("19 Poeple, Pass 9, Swinging, Who can live: " + whoCanLive(19, 9));
    System.out.println("60 Poeple, Pass 20, Swinging, Who can live: " + whoCanLive(60, 20));
    System.out.println("39 Poeple, Pass 15, Swinging, Who can live: " + whoCanLive(39, 15));
  }
}
