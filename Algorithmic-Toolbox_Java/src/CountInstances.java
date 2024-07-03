import java.util.Scanner;

public class CountInstances {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(mergeAndCount(a, 0, a.length));
    }

    private static int mergeAndCount(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            int leftCount = mergeAndCount(arr, left, mid);
            int rightCount = mergeAndCount(arr, mid + 1, right);

            int mergeCount = merge(arr, left, mid, right);

            return leftCount + rightCount + mergeCount;
        }
        return 0;
    }

    private static int merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int count = 0;

        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (i <= arr.length && j <= right && arr[i] > arr[j]) {
                count += mid - i + 1;
                temp[k++] = arr[j++];
            } else {
                temp[k++] = arr[i++];
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        System.arraycopy(temp, 0, arr, left, temp.length);
        return count;
    }
}