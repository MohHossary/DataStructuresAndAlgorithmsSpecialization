import java.util.*;

public class PointsAndSegments {

    public static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Segment[] segments = new Segment[starts.length];
        for (int i = 0; i < segments.length; i++) {
            segments[i] = new Segment(starts[i], ends[i]);
            if (segments[i].start < min)
                min = segments[i].start;
            if (segments[i].end > max)
                max = segments[i].end;
        }
//        Map<Integer, Integer> cashedCounts = new HashMap<>(); // first is point, second is cnt
        int[] cnt = new int[points.length];
        Arrays.sort(segments);


//        for (int coord = min; coord <= max; coord++) {
//            int startI = binarySearch(segments, coord, 0, segments.length - 1);
//            int endI = binarySearch(segments, coord + 1, 0, segments.length - 1) - 1;
//            for (int i = endI; i <= startI; i--) {
//                if (i >= 0 && segments[i].end < coord)
//                    break;
//            }
//        }

        for (int pointIndex = 0; pointIndex < points.length; pointIndex++) {
                int count;
                count = 0;
                int startIdx = 0;
                int endIdx = binarySearch(segments, points[pointIndex] + 1, 0, segments.length);
                for (int j = startIdx; j < endIdx; j++) {
                    if (segments[j].end >= points[pointIndex]) {
                        count++;
                    }
                }
                cnt[pointIndex] = count;
                // cache it
//                cashedCounts.put(points[pointIndex], count);
        }
        return cnt;
    }

    /**find the index of first element >= query
     *
     * @param array
     * @param query
     * @param low
     * @param high exclusive
     * @return
     */
    public static int binarySearch(Segment[] array, int query, int low, int high) {
        if (low > high)
            return high;
        if (low == high)
            return high;

        int mid = (low + high) / 2;
        if (array[mid].start > query)
            return binarySearch(array, query, low, mid);
        else if (array[mid].start < query)
            return binarySearch(array, query, mid + 1, high);
        else { // (array[mid].start == query)
            if (mid == 0 || array[mid - 1].start < query) {
                return mid;
            }else {
                return binarySearch(array, query, low, mid);
            }
        }
//        return mid;
    }


    public static int binarySearchStart(Segment[] array, int value, int start, int end) {
        int low = start;
        int high = end;
        int mid = (low + high) / 2;
        if (array[mid].start == value) {
            if (mid != 0 && array[mid - 1].start == value) {
            binarySearchStart(array, value, low, mid);
            }
            else {
                return mid;
            }
        }
        if (array[mid].start > value)
            binarySearchStart(array, value, low, mid);
        else
            binarySearchStart(array, value, mid + 1, end);
        return mid;
    }

    public static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static class Segment implements Comparable<Segment> {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Segment o) {
            if (this.start == o.start)
                return this.end - o.end;
            else
                return this.start - o.start;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

