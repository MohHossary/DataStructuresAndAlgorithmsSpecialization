public class Utility{



    // Function to solve the knapsack problem
    public static int knapsack(int W, int[] weights, int n) {
        int[][] dp = new int[n + 1][W + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(weights[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Return the maximum weight that can be put in the knapsack of capacity W
        return dp[n][W];
    }

    public static boolean canPartitionIntoThree(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        // If the total sum is not divisible by 3, we cannot partition it into three equal subsets
        if (sum % 3 != 0) {
            return false;
        }

        int target = sum / 3;
        int n = nums.length;
        boolean[][][] dp = new boolean[n + 1][target + 1][target + 1];

        dp[0][0][0] = true;

        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= target; j++) {
                for (int k = 0; k <= target; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= num) {
                        dp[i][j][k] |= dp[i - 1][j - num][k];
                    }
                    if (k >= num) {
                        dp[i][j][k] |= dp[i - 1][j][k - num];
                    }
                }
            }
        }

        return dp[n][target][target];
    }

    public static void main(String[] args) {
        int[] weights = {1, 4, 8};
        int W = 10;
        int n = weights.length;
        System.out.println("Maximum weight in knapsack = " + knapsack(W, weights, n));
    }

    public static String LCS(String s, String t) {
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

        PositionWithString[][] memo = new PositionWithString[s.length() + 1][t.length() + 1];
        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < t.length() + 1; j++) {
                memo[i][j] = new PositionWithString();
            }
        }

        for (int i = 0; i <= s.length(); i++)
            memo[i][0].value = i;
        for (int i = 0; i <= t.length(); i++)
            memo[0][i].value = i;

        int insert, delete, replace;
        PositionWithString currentPosition, prevPosition;
        int value;
        String c;

        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                currentPosition = memo[i][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    prevPosition = memo[i - 1][j - 1];
                    value = prevPosition.value;
                    c = s.substring(i - 1, i); // also = t.charAt(j - 1)
                } else {
                    insert = memo[i][j - 1].value + 1;
                    delete = memo[i - 1][j].value + 1;
                    replace = memo[i - 1][j - 1].value + 1;
                    c = "";

                    if (insert < delete) {
                        // sure not delete
                        if (insert > replace) {
                            // sure replace
                            value = replace;
//                            c = "?"; //= "("+s.charAt(i)+"|" + t.charAt(j)+")";
                            prevPosition = memo[i - 1][j - 1];
                        } else {
                            //sure insert
                            prevPosition = memo[i][j - 1];
                            value = insert;
//                            c = t.substring(j - 1, j);
                        }
                    } else {
                        // sure not insert
                        if (delete < replace) {
                            // sure delete
                            value = delete;
//                            c = "";
                            prevPosition = memo[i - 1][j];
                        } else {
                            // sure replace
                            value = replace;
//                            c = "?"; //= "("+s.charAt(i)+"|" + t.charAt(j)+")";
                            prevPosition = memo[i - 1][j - 1];
                        }
                    }
                }
                currentPosition.prevPosition = prevPosition;
                currentPosition.value = value;
                currentPosition.c = c;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        PositionWithString pos = memo[s.length()][t.length()];
        while (pos.prevPosition != null) {
            stringBuilder.append(pos.c);
            pos = pos.prevPosition;
        }
        return stringBuilder.reverse().toString();
    }

    public static <T> void printArr(T[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.format("%6s\t", arr[i][j], arr[i][j]);
            }
            System.out.println();
        }
    }


    public static void printArr (long[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.format("%6s\t", arr[i][j], arr[i][j]);
            }
            System.out.println();
        }
    }

    public static void printArr (int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.format("%6s\t", arr[i][j], arr[i][j]);
            }
            System.out.println();
        }

    }

//    public static void main(String[] args) {
//        Utility.<Integer>printArr(new Integer[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
//    }

    static class PositionWithString {
        String c;
        int value;
        PositionWithString prevPosition;
    }
}
