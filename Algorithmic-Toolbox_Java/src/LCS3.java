import java.util.*;

public class LCS3 {

    public static int LCSWithoutPos (int[] s, int[] t, boolean allowReplace) {

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
                if (s[i - 1] != t[j - 1]) {
                    insert = memo[i][j - 1] + 1;
                    delete = memo[i - 1][j] + 1;
                    replace = allowReplace ? memo[i - 1][j - 1] + 1 : Integer.MAX_VALUE;

                    memo[i][j] = Math.min(Math.min(insert, delete), replace);
                } else
                    memo[i][j] = memo[i - 1][j - 1] + 1;
            }

        return memo[s.length][t.length];
    }

    static class PositionWithInteger {
        Integer c;
        int value;
        PositionWithInteger prevPosition;
    }

    public static int LCS(int[] s, int[] t, int[] u) {

        int[][][] memo = new int[s.length + 1][t.length + 1][u.length + 1];

        for (int i = 0; i <= s.length; i++) {
            for (int j = 0; j <= t.length; j++) {
                for (int k = 0; k <= u.length; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        memo[i][j][k] = 0;
                    } else if (s[i - 1] == t[j - 1] || s[i - 1] == u[k - 1]) {
                        memo[i][j][k] = memo[i - 1][j - 1][k - 1] + 1;
                    } else
                        memo[i][j][k] = Math.max(Math.max(Math.max(memo[i - 1][j][k], memo[i - 1][j - 1][k]), Math.max(memo[i][j  - 1][k - 1], memo[i][j - 1][k])), Math.max(memo[i][j][k - 1], memo[i - 1][j][k - 1]));
                }
            }
        }
        return memo[s.length][t.length][u.length];
    }

    public static int ThreeDimensionLCS(int[] s, int[] t, int[] u) {

        /*
          E D I T I N G (S, i)
      0 0 1 2 3 4 5 6 7
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
 (T, j) ----------------

      A
      1
        ----------------
        |1 ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        ----------------

      B
      2
        ----------------
        |2 ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        ----------------

     (U, k)
    */

        int[][][] memo = new int[s.length + 1][t.length + 1][u.length + 1];
        int insert, delete, replace;

        { // pre-initial conditions
            for (int i = 0; i < s.length + 1; i++) {
                memo[i][0][0] = i;
            }
            for (int i = 0; i < t.length + 1; i++) {
                memo[0][i][0] = i;
            }
            for (int i = 0; i < u.length + 1; i++) {
                memo[0][0][i] = i;
            }
        }
        { // initial conditions
            for (int i = 1; i < s.length; i++) {
                for (int k = 1; k < u.length; k++) {
                    if (s[i - 1] != s[i] || u[k - 1] != u[k]) {
                        insert = memo[i - 1][0][k];
                        delete = memo[i][0][k - 1];
                        replace = memo[i - 1][0][k - 1];
                        memo[i][0][k] = Math.min(Math.min(insert, delete), replace) + 1;
                    } else {
                        memo[i][0][k] = memo[i - 1][0][k - 1];
                    }
                }
            }
            for (int i = 1; i < s.length; i++) {
                for (int j = 1; j < t.length; j++) {
                    if (s[i - 1] != s[i] || t[j - 1] != t[j]) {
                        insert = memo[i - 1][j][0];
                        delete = memo[i][j - 1][0];
                        replace = memo[i - 1][j - 1][0];
                        memo[i][j][0] = Math.min(Math.min(insert, delete), replace) + 1;
                    } else {
                        memo[i][j][0] = memo[i - 1][j - 1][0];
                    }
                }
            }
            for (int j = 1; j < t.length; j++) {
                for (int k = 1; k < u.length; k++) {
                    if (t[j - 1] != t[j] || u[k - 1] != u[k]) {
                        insert = memo[0][j][k - 1];
                        delete = memo[0][j - 1][k];
                        replace = memo[0][j - 1][k - 1];
                        memo[0][j][k] = Math.min(Math.min(insert, delete), replace) + 1;
                    } else {
                        memo[0][j][k] = memo[0][j - 1][k - 1];
                    }
                }
            }
        }
        { // Actual LCS Algorithm
            for (int i = 1; i < s.length; i++) {
                for (int j = 1; j < t.length; j++) {
                    for (int k = 1; k < u.length; k++) {
                        if (s[i - 1] != s[i] || t[j - 1] != t[j] || u[k - 1] != u[k]) {
                            insert = memo[i - 1][j][k];
                            delete = memo[i][j - 1][k];
                            replace = memo[i][j][k - 1] + 1;
                            memo[i][j][k] = Math.min(Math.min(insert, delete), replace) + 1;
                        } else {
                            memo[i][j][k] = memo[i - 1][j - 1][k - 1];
                        }
                    }
                }
            }
        }
        return memo[s.length][t.length][u.length];
    }

    public static int ThreeDimensionLCSRecursive(int[] s, int[] t, int[] u, int[][][] memo, int levelI, int levelJ, int levelK) {
        /*
          E D I T I N G (S, levelI)
      0 0 1 2 3 4 5 6 7
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
 (T, levelJ) ----------------

      A
      1
        ----------------
        |1 ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        ----------------

      B
      2
        ----------------
        |2 ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        |? ? ? ? ? ? ? ?|
        ----------------

     (U, levelK)
    */

        int insert, delete;
        int startI = Math.max(1, levelI), startJ = Math.min(1, levelJ), startK = Math.max(1, levelK);

        if (levelI == 0) {
            for (int j = 0; j < t.length; j++) {
                memo[0][j][0] = j;
            }
            for (int k = 0; k < u.length; k++) {
                memo[0][0][k] = k;
            }
            startJ = startK = 1;
        }
        if (levelJ == 0) {
            for (int i_ = 0; i_ < s.length; i_++) {
                memo[0][0][i_] = i_;
            }
            for (int k_ = 0; k_ < u.length; k_++) {
                memo[0][0][k_] = k_;
            }
            startI = startK = 1;
        }
        if (levelK == 0) {
            for (int i_ = 0; i_ < s.length; i_++) {
                memo[i_][0][0] = i_;
            }
            for (int j_ = 0; j_ < t.length; j_++) {
                memo[0][j_][0] = j_;
            }
            startI = startJ = 1;
        }

        { // solve faces of cube
            for (int i = startI; i < s.length; i++) {
                for (int j = startJ; j < t.length; j++) {
                    if (s[i - 1] == t[j - 1]) {
                        memo[i][j][levelK] = memo[i - 1][j - 1][levelK] + 1;
                    } else {
                        insert = memo[i - 1][j][levelK] + 1;
                        delete = memo[i][j - 1][levelK] + 1;
                        memo[i][j][levelK] = Math.min(insert, delete);
                    }
                }
            }
            for (int j = startJ; j < t.length; j++) {
                for (int k = startK; k < u.length; k++) {
                    if (t[j - 1] == u[k - 1]) {
                        memo[levelI][j][k] = memo[levelI][j - 1][k - 1] + 1;
                    } else {
                        insert = memo[levelI][j - 1][k] + 1;
                        delete = memo[levelI][j][k - 1] + 1;
                        memo[levelI][j][k] = Math.min(insert, delete);
                    }
                }
            }
            for (int i = startI; i < s.length; i++) {
                for (int k = startK; k < u.length; k++) {
                    if (s[i - 1] == u[k - 1]) {
                        memo[i][levelJ][k] = memo[i - 1][levelJ][k - 1] + 1;
                    } else {
                        insert = memo[i - 1][levelJ][k] + 1;
                        delete = memo[i][levelJ][k - 1] + 1;
                        memo[i][levelJ][k] = Math.min(insert, delete);
                    }
                }
            }
        }

        if (levelI == s.length && levelJ == t.length && levelK == u.length) {
            return memo[s.length][t.length][u.length];
        }

        return ThreeDimensionLCSRecursive(s, t, u, memo, Math.min(levelI + 1, s.length), Math.min(levelJ + 1, t.length), Math.min(levelK + 1, u.length));
    }




    public static int lcs3(int[] a, int[] b, int[] c) {
//        int[][] lists = new int[][]{a, b, c};
//        Arrays.sort(lists, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1.length - o2.length;
//            }
//        });
//
//        int[] lcsArr = null;
//        for (int i = 0; i < lists.length - 2; i++) {
//            lcsArr = LCS(lists[i], lists[i + 1]);
//        }
//        return LCS(lcsArr, lists[lists.length - 1]).length;
//        return LCS(LCS(lists[0], lists[1]), lists[2]).length;
//        int[][][] memo = new int[a.length + 1][b.length + 1][c.length + 1];
//        for (int i = 0; i <= a.length; i++) {
//            for (int j = 0; j <= b.length; j++) {
//                for (int k = 0; k <= c.length; k++) {
//                    memo[i][j][k] = 1000;
//                }
//            }
//        };
//        return (a.length + b.length + c.length) - ThreeDimensionLCSRecursive(a, b, c, memo, 0, 0, 0);
//        return ThreeDimensionLCSRecursive(a, b, c, memo, 0, 0, 0);
        return LCS(a, b, c);
    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(LCS(new int[]{1, 2, 3, 5}, new int[]{1, 2, 2, 2, 4, 5})));


        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

