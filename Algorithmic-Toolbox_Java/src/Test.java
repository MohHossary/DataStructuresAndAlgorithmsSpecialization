//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.Comparator;
//
public class Test {
//
//    public static void mergeSort(int[] arr, int[] temp, int start, int end) {
//
//        int length = end - start + 1;
//        if (length <= 1)
//            return;
//
//        int mid = start + length / 2;
//
//        mergeSort(arr, temp, start, mid -1);
//        mergeSort(arr, temp, mid, end);
//
//        merge(arr, temp, start, mid, end);
//    }
//
//    public static void merge(int[] arr, int[] temp, int start, int mid, int end) {
//
//        int i = start, j = mid, k = start;
//        while (i < mid & j <= end) {
//            if (arr[i] <= arr[j])
//                temp[k++] = arr[i++];
//            else
//                temp[k++] = arr[j++];
//        }
//        while (i < mid)
//            temp[k++] = arr[i++];
//        while (j <= end)
//            temp[k++] = arr[j++];
//
//        System.arraycopy(temp, start, arr, start, end - start + 1);
//    }
//
//    public static int LCS3(int[] arr1, int[] arr2, int[] arr3) {
//        int[][] lists = new int[3][];
//        lists[0] = arr1;
//        lists[1] = arr2;
//        lists[2] = arr3;
//
//        Arrays.sort(lists, Comparator.comparingInt(o -> o.length));
//        int[][] backtracks;
//        int insert, delete;
//        {
//            int[][] memo = new int[lists[0].length + 1][lists[1].length + 1];
//            for (int i = 0; i <= lists[0].length; i++)
//                memo[i][0] = i;
//            for (int i = 0; i <= lists[1].length; i++)
//                memo[0][i] = i;
//
//            for (int i = 1; i <= lists[0].length; i++)
//                for (int j = 1; j <= lists[1].length; j++) {
//                    if (lists[1][j - 1] == lists[0][i - 1]) {
//                        memo[i][j] = memo[i - 1][j - 1] + 1;
//                    } else {
//                        insert = memo[i][j - 1] + 1;
//                        delete = memo[i - 1][j] + 1;
//
//                        memo[i][j] = Math.min(insert, delete);
//                    }
//                }
//
//            backtracks = backtrackwrapper(memo);
//
//        }
//
//        ArrayList<Integer> results = new ArrayList<>();
//
//
//        for (int[] track : backtracks) {
//            int[][] memo = new int[track.length + 1][lists[2].length + 1];
//            for (int i = 0; i <= track.length; i++)
//                memo[i][0] = i;
//            for (int i = 0; i <= lists[2].length; i++)
//                memo[0][i] = i;
//
//            for (int i = 1; i <= track.length; i++)
//                for (int j = 1; j <= lists[2].length; j++) {
//                    if (lists[2][j - 1] == track[i - 1]) {
//                        memo[i][j] = memo[i - 1][j - 1] + 1;
//                    } else {
//                        insert = memo[i][j - 1] + 1;
//                        delete = memo[i - 1][j] + 1;
//
//                        memo[i][j] = Math.min(insert, delete);
//                    }
//                }
//            results.add(memo[track.length][lists[2].length]);
//        }
//
//        int result = Collections.min(results);
//
//        return arr1.length + arr2.length + arr3.length - result;
//    }
//
//    private static int[][] backtrackwrapper(int[][] memo) {
//        return backtrack(memo, new ArrayList<>(), memo.length, memo[0].length);
//    }
//
//    private static int[][] backtrack(int[][] memo, ArrayList<Integer> path, int x, int y) {
//        int insert, delete, match;
//        insert = memo[x][y - 1] + 1;
//        delete = memo[x - 1][y] + 1;
//        match = memo[x - 1][y - 1];
//        int choice = Math.min(Math.min(insert, delete), match);
//        if (insert == delete || insert == choice)
//    }
    public static void main(String[] args) {
        System.out.println(PlacingParentheses.eval(6, '+', 5));
    }
}
