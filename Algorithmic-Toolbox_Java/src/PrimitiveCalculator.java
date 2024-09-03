import javax.sound.midi.Sequence;
import java.util.*;

public class PrimitiveCalculator {

    public static ArrayList<Integer> optimal_sequence(int n) {
        if (n == 1)
            return new ArrayList<>(List.of(1));
        if (n <= 3)
            return new ArrayList<>(Arrays.asList(1, n));


        int[][] chart = new int[n + 1][2];  // Chart of '#' of operations and 'prev' pairs, corresponding w/ their idx

        for (int i = 4; i <= n; i++) {  // Build up chart as we iterate through numbers
            int can = Integer.MAX_VALUE; // Use Integer.MAX_VALUE as a large number
            int prev = can;

            // If 'i' is divisible by 3
            if (i % 3 == 0) {
                int i_ = i / 3;
                if (chart[i_][0] < can) {
                    can = chart[i_][0];
                    prev = i_;
                }
            }

            // If 'i' is divisible by 2
            if (i % 2 == 0) {
                int i_ = i / 2;
                if (chart[i_][0] < can) {
                    can = chart[i_][0];
                    prev = i_;
                }
            }

            // If the number ('i' - 1)
            int i_ = i - 1;
            if (chart[i_][0] < can) {
                can = chart[i_][0];
                prev = i_;
            }

            chart[i][0] = can + 1;  // Adding 1 to include the extra step to get from 'prev' to 'i'
            chart[i][1] = prev;
        }

        // Build list of "previous" numbers building up to 'n'
        ArrayList<Integer> result = new ArrayList<>();
        result.add(n);
        int prev = chart[n][1];
        for (int i = 0; i < chart[n][0]; i++) {
            result.add(prev);
            prev = chart[prev][1];
        }
        result.add(1);

        Collections.reverse(result);
        return result;

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        ArrayList<Integer> sequence = optimal_sequence(n);
        System.out.println(sequence.size() - 1);
        for (int x : sequence) {
            System.out.print(x + " ");
        }
    }
}

