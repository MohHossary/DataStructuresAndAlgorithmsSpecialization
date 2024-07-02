import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        int[] points = new int[segments.length];
        for (int i = 0; i < segments.length; i++) {
            points[i] = i;
        }
        Vector<Segment> finished_segments = new Vector<Segment>();
        for (int point : points) {
            int amount_of_lines_covered = 0;
            for (int i = 0; i < segments.length; i++) {
                Segment segment = segments[i];
                if (!finished_segments.contains(segment)  && segment.start <= point && point <= segment.end) {
                    amount_of_lines_covered++;
                }
            }
            System.out.println(amount_of_lines_covered);
        }

        return points;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static class StartComparator implements Comparator<Segment>{

        @Override
        public int compare(Segment o1, Segment o2) {
            return o1.start - o2.start;
        }
    }

    private static class EndComparator implements Comparator<Segment>{

        @Override
        public int compare(Segment o1, Segment o2) {
            return o1.end - o2.end;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start - 1, end - 1);
        }
        int[] points = optimalPoints(segments);
//        System.out.println(points.length);
//        for (int point : points) {
//            System.out.print(point + " ");
//        }
    }
}
