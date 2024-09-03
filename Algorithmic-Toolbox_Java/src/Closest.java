import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

import static java.lang.Math.min;


public class Closest {

    static PointComparator comparator = new PointComparator();

    public static double naiveMinimalDistance(Point[] points) {
        double minDistance = Double.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                double distance = points[i].calcDistanceTo(points[j]);
                if (minDistance > distance)
                    minDistance = distance;
            }
        }
        return minDistance;
    }

    public static int binarySearch(Point[] array, double query, int low, int high) {
        if (low >= high)
            return high;

        int mid = ((low + high) / 2);
        if (array[mid].x > query)
            return binarySearch(array, query, low, mid);
        else if (array[mid].x < query)
            return binarySearch(array, query, mid + 1, high);
        else { // (array[mid].x == query)
            if (mid == 0 || array[mid - 1].x < query) {
                return mid;
            } else {
                return binarySearch(array, query, low, mid);
            }
        }
    }

    static class PointComparator implements Comparator<Point> {
        @Override
        public int compare(Point o1, Point o2) {
            return o1.y == o2.y ? Long.signum(o1.x - o2.x) : Long.signum(o1.y - o2.y);
        }
    }

    static class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double calcDistanceTo(Point p) {
            return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
        }

        @Override
        public int compareTo(Point o) {
            return o.x == x ? Integer.signum(y - o.y) : Integer.signum(x - o.x);
        }

        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }

    static double getMinDistance(Point[] points, int low, int mid, int high, double minDistance, double separator) {
        for (int i = low; i < high - 1; i++) {
            for (int j = i + 1; j < min(high, i + 8); j++) {
                double currentDist = points[i].calcDistanceTo(points[j]);
                if (currentDist < minDistance)
                    minDistance = currentDist;
            }
        }
        return minDistance;
    }

    static double minimalDistance(Point[] points, int lowIdx, int highIdx) {
        double ans = Double.MAX_VALUE;
        if (highIdx - lowIdx <= 1) {
            return ans;
        }
        else if (highIdx - lowIdx == 2) {
            return points[lowIdx].calcDistanceTo(points[highIdx - 1]);
        }
//      implement
        Arrays.sort(points);
//        System.out.println(Arrays.toString(points));
        int separatorIndex = ((highIdx + lowIdx) / 2);
        float separator = (((highIdx - lowIdx) % 2) == 1) ? points[separatorIndex].x :
                (points[separatorIndex - 1].x + points[separatorIndex].x) / 2.F;
        //now find minimal distance in left and right halves
        double left = minimalDistance(points, lowIdx, separatorIndex);
        double right = minimalDistance(points,  separatorIndex, highIdx);
        double d = min(left, right);
//        Point[] left = Arrays.copyOfRange(points, 0, separator);
//        Point[] right = Arrays.copyOfRange(points, separator, points.length);
        int lStripI = binarySearch(points, separator - d,  lowIdx, separatorIndex);
        int rStripI = binarySearch(points, separator + d, separatorIndex, highIdx);
        Arrays.sort(points, lStripI, rStripI, comparator);
//        System.out.println(Arrays.toString(points));
        ans = getMinDistance(points, lStripI, separatorIndex, rStripI, d, separator);
        return ans;
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            points[i] = new Point(nextInt(), nextInt());
        }
        System.out.println(minimalDistance(points, 0, n));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
