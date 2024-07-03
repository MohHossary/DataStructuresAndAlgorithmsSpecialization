import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Sorting {
    private static Random random = new Random();

    private static int[] partition3(int[] a, int l, int r) {
        int j1 = l;
        int j2 = j1;
        int pivot = a[l];
        for (int i = j1 + 1; i <= r; i++) {
            if (a[i] < pivot) {
                circulate(a, j1++, ++j2, i);
            }
            else if (a[i] == pivot) {
                exchange(a, ++j2, i);
            }
        }

      return new int[]{j1, j2};
    }

    private static int partition2(int[] a, int l, int r) {
        int x = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= x) {
                j++;
                exchange(a, i, j);
            }
        }
        exchange(a, l, j);
        return j;
    }

    private static void randomizedQuickSort(int[] a, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l) + l;
        exchange(a, l, k);
        int[] partitions = partition3(a, l, r);
        int lowerBound = partitions[0];
        int upperBound = partitions[1];
        randomizedQuickSort(a, l,  lowerBound - 1);
        randomizedQuickSort(a, upperBound + 1, r);
    }

    private static void exchange(int[] a, int x, int y) {
        int t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    private static void circulate(int[] a, int x, int y, int z) {
        int t = a[z];
        a[z] = a[y];
        a[y] = a[x];
        a[x] = t;
    }

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        randomizedQuickSort(a, 0, n - 1);
        for (int i = 0; i < n; i++) {
            System.out.print(a[i] + " ");
        }
    }

    static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        FastScanner(InputStream stream) {
            try {
                br = new BufferedReader(new InputStreamReader(stream));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

