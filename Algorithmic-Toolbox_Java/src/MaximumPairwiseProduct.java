import java.util.Arrays;

public class MaximumPairwiseProduct {

    int[] arr = new int[]{2, 5, 10, 0, 3, 7, 10, 9, 10};

    public static int[] max_product_2indices(int[] arr) {
    int max_i = -1;
    int max_j = -1;
    int max_product = -1;  // arr[max_i]*arr[max_j]

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                int product = arr[i] * arr[j];
                if (product > max_product) {
                    max_product = product;
                    max_i = i;
                    max_j = j;
                }
            }
        }
        return new int[]{max_i, max_j};

    }


    public static int[] max_product_XIndices(int[] arr, int x) {
        int[] ret = new int[x];
        int[] ret_i = new int[x];
        Arrays.fill(ret, -1);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > ret[0]) {
                ret[0] = arr[i];
                ret_i[0] = i;
                for (int j = 1; j < ret.length; j++) {
                    if (ret[j] < ret[j - 1]) {
                        swap(ret, j, j - 1);
                        swap(ret_i, j, j - 1);
                    }
                }
            }
        }
        Arrays.sort(ret_i);
        return ret_i;
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        MaximumPairwiseProduct obj = new MaximumPairwiseProduct();
        System.out.println(Arrays.toString(max_product_2indices(obj.arr)));
        System.out.println(Arrays.toString(max_product_XIndices(obj.arr, 3)));
    }
}
