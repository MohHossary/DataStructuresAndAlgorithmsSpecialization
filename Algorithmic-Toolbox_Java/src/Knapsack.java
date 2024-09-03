import java.util.*;

public class Knapsack {

    static class Item{
        final int weight;
        public Item(int weight){
            this.weight = weight;
        }
    }

    static int optimalWeight(int capacity, int[] items) {

        int n = items.length;
        int[][] memo = new int[n + 1][capacity + 1];
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    memo[i][w] = 0;
                } else if (items[i - 1] <= w) {
                    memo[i][w] = Math.max(items[i - 1] + memo[i - 1][w - items[i - 1]], memo[i - 1][w]);
                } else {
                    memo[i][w] = memo[i - 1][w];
                }
            }
        }
        return memo[n][capacity];

//        int result = 0;
//        for (int i = 0; i < items.length; i++) {
//          if (result + items[i] <= capacity) {
//            result += items[i];
//          }
//        }
//        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int W, n;
        W = scanner.nextInt();
        n = scanner.nextInt();
        int[] w = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = scanner.nextInt();
        }
        System.out.println(optimalWeight(W, w));
    }
}

