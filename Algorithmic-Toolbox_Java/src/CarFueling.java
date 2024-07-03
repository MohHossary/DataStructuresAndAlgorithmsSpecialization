import java.util.Scanner;

public class CarFueling {
    static int computeMinRefills(int dist, int tank, int[] stops) {
        int minNumOfStops = 0;
        int currDist = 0;
        int currStop = -1;
        int confirmedStop = -1;
        int prospectStop = -1;

        while (currStop != dist) {
            if (currDist + tank >= dist) {
                return minNumOfStops;
            }
            prospectStop = currStop + 1;
            while (prospectStop < stops.length && stops[prospectStop] - currDist <= tank) {
                confirmedStop = prospectStop;
                prospectStop++;
            }
            if (confirmedStop == currStop) {
                return -1;
            } else {
                currStop = confirmedStop;
                currDist = stops[currStop];
                minNumOfStops++;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int dist = scanner.nextInt();
        int tank = scanner.nextInt();
        int n = scanner.nextInt();
        int stops[] = new int[n];
        for (int i = 0; i < n; i++) {
            stops[i] = scanner.nextInt();
        }

        System.out.println(computeMinRefills(dist, tank, stops));
    }
}
