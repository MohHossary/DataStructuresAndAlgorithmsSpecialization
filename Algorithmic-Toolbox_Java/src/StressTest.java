import java.time.Clock;
import java.util.Arrays;
import java.util.Objects;



public class StressTest {
    public static void main(String[] args) {
        boolean failed = false;
        while (!failed) {
            int n = (int) (Math.random() * 10) + 1;
//            int m = (int) (Math.random() * 100_000) + 1;
            Closest.Point[] points = new Closest.Point[n];
            for (int i = 0; i < n; i++) {
                int x = (int) (Math.random() * 200) - 100 + 1;
                int y = (int) (Math.random() * 200) - 100 + 1;
//                int l = (int) (Math.random() * 100_000_000) * (Math.random()> 0.5 ? 1 : -1);
//                int r = (int) (Math.random() * (2* 100_000_000 - l)) + l;
                points[i] = new Closest.Point(x, y);
//                segments[i] = new PointsAndSegments.Segment(l, r);
//                starts[i] = l;
//                ends[i] = r;
            }
//            for (int i = 0; i < m; i++) {
//                points[i] = (int) (Math.random() * 200_000_001) - 100_000_000;
//            }
            System.out.println("started fast stress test");
            long before_fast = System.nanoTime();
            double fast = Closest.minimalDistance(points, 0, points.length);
            long after_fast = System.nanoTime();
            System.out.println("started naive stress test");
            long before_naive = System.nanoTime();
            double naive = Closest.naiveMinimalDistance(points);
            long after_naive = System.nanoTime();
            if (!(Math.abs(naive - fast) < 0.001)) {
                failed = true;
//                System.out.println("starts: " + Arrays.toString(starts));
//                System.out.println("ends: " + Arrays.toString(ends));
                System.out.println("points: " + Arrays.toString(points));
//                System.out.println("number of segments: " + segments.length);
                System.out.println("number of points: " + points.length);
                System.out.println("naive: " + naive);
                System.out.println("naive time: " + ((after_naive - before_naive) / 1_000_000_000) + " seconds");
                System.out.println("fast: " + fast);
                System.out.println("fast time: " + ((after_fast - before_fast) / 1_000_000_000) + " seconds");
                System.out.println("Failed");
            }
            else {
//                System.out.println("starts: " + Arrays.toString(starts));
//                System.out.println("ends: " + Arrays.toString(ends));
                System.out.println("points: " + Arrays.toString(points));
//                System.out.println("number of segments: " + segments.length);
                System.out.println("number of points: " + points.length);
                System.out.println("naive: " + naive);
                System.out.println("naive time: " + ((after_naive - before_naive) / 1_000_000_000) + " seconds");
                System.out.println("fast: " + fast);
                System.out.println("fast time: " + ((after_fast - before_fast) / 1_000_000_000) + " seconds");
                System.out.println("Succeeded");
            }
        }
    }
}
