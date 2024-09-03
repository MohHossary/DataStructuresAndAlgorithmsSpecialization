import java.util.*;

class EditDistance {

  public static int EditDistance(String s, String t) {

    /*
          E D I T I N G (S)
        0 1 2 3 4 5 6 7
        ----------------
      0 |0 1 2 3 4 5 6 7|
    D 1 |1 ? ? ? ? ? ? ?|
    I 2 |2 ? ? ? ? ? ? ?|
    S 3 |3 ? ? ? ? ? ? ?|
    T 4 |4 ? ? ? ? ? ? ?|
    A 5 |5 ? ? ? ? ? ? ?|
    N 6 |6 ? ? ? ? ? ? ?|
    C 7 |7 ? ? ? ? ? ? ?|
    E 8 |8 ? ? ? ? ? ? ?|
   (T)  ----------------
    */

    int[][] memo = new int[s.length() + 1][t.length() + 1];
    for (int i = 0; i <= s.length(); i++)
      memo[i][0] = i;
    for (int i = 0; i <= t.length(); i++)
      memo[0][i] = i;

    for (int i = 1; i <= s.length(); i++)
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) != t.charAt(j - 1)) {
          int insert = memo[i][j - 1] + 1;
          int delete = memo[i - 1][j] + 1;
          int replace = memo[i - 1][j - 1] + 1;

          memo[i][j] = Math.min(Math.min(insert, delete), replace);
        } else
          memo[i][j] = memo[i - 1][j - 1];
      }

    return memo[s.length()][t.length()];
  }

  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(EditDistance(s, t));
  }

}
