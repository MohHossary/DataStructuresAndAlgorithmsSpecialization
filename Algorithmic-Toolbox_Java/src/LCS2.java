import java.util.*;

public class LCS2 {

    public static int EditDistance2 (int[] s, int[] t, boolean allowReplace) {

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

        int[][] memo = new int[s.length + 1][t.length + 1];
        for (int i = 0; i <= s.length; i++)
            memo[i][0] = i;
        for (int i = 0; i <= t.length; i++)
            memo[0][i] = i;

        int insert, delete, replace;
        for (int i = 1; i <= s.length; i++)
            for (int j = 1; j <= t.length; j++) {
                if (t[j - 1] == s[i - 1]) {
                    memo[i][j] = memo[i - 1][j - 1] + 1;
                } else {
                    insert = memo[i][j - 1] + 1;
                    delete = memo[i - 1][j] + 1;
                    replace = allowReplace ? memo[i - 1][j - 1] + 1 : Integer.MAX_VALUE;

                    memo[i][j] = Math.min(Math.min(insert, delete), replace);
                }
            }

        return memo[s.length][t.length];
    }


    private static int lcs2(int[] a, int[] b) {
        return (a.length + b.length) - EditDistance2(a, b, false);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        int[] b = new int[m];
        for (int i = 0; i < m; i++) {
            b[i] = scanner.nextInt();
        }

        System.out.println(lcs2(a, b));
    }
}

