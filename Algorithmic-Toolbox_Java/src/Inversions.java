import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right - left < 1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave + 1, right);

        numberOfInversions += merge(a, b, left, ave, right);

        return numberOfInversions;
    }

    private static long merge(int[] arr, int[] temp, int start, int mid, int end) {

        int numOfInversions = 0;

        int i = start, j = mid + 1, k = start;
        while (i <= mid & j <= end) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else{
                temp[k++] = arr[j++];
                numOfInversions += (mid - i + 1);
            }
        }
        while (i <= mid) {
            temp[k++] = arr[i++];
        }
        while (j <= end) {
            temp[k++] = arr[j++];
//            numOfInversions++;
        }

        System.arraycopy(temp, start, arr, start, end - start + 1);

        return numOfInversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length - 1));
    }
}

