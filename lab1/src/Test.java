import java.util.*;
public class Test {
  public static void main(String[] args) {
    Test t = new Test();
    //t.compare();
    t.testPrint();
  }

  public void testPrint() {
    int[][] b1 = new int[5][5];
    int[][] b2 = new int[5][5];
    int[][] b3 = new int[5][5];
    for (int i = 0; i < 5; i ++) {
      for (int j = 0; j < 5; j++) {
        b1[i][j] = i * j;
        b2[i][j] = i * j;
        b3[i][j] = i * j;
      }
    }

    Vehicle v1 = new Vehicle(1, null, 0, null);
    Vehicle v2 = new Vehicle(2, null, 0, null);

    AIAction a1 = new AIAction(v1, Vehicle.Direction.LEFT);
    AIAction a2 = new AIAction(v2, Vehicle.Direction.RIGHT);

    Board bd1 = new Board(b1);
    Board bd2 = new Board(b2);
    bd2.setActionTaken(a1);
    bd2.setParent(bd1);
    Board bd3 = new Board(b3);
    bd3.setActionTaken(a2);
    bd3.setParent(bd2);

    AI ai = new AI(b1, null);
    List<AIAction> path = ai.buildPath(bd3);

  }

  public void compare() {
    int[][] b1 = new int[5][5];
    int[][] b2 = new int[5][5];
    for (int i = 0; i < 5; i ++) {
      for (int j = 0; j < 5; j++) {
        b1[i][j] = i * j;
        b2[i][j] = i * j;
      }
    }
    Board bd1 = new Board(b1);
    Board bd2 = new Board(b2);

    Set<Board> set = new HashSet<>();
    set.add(bd1);

    if (set.contains(bd2)) {
      System.out.println("success");
    } else {
      System.out.println("fail");
    }
  }

}
