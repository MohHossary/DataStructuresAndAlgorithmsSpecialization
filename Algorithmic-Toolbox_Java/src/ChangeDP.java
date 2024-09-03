import java.util.Arrays;
import java.util.Scanner;

public class ChangeDP {
    private static int getChange(int m) {

        int[] memo = new int[m + 1];

        if (m <= 2)
            return m;
        else if (m <= 4)
            return 1;

        memo[0] = 0;
        memo[1] = 1;
        memo[2] = 2;
        memo[3] = 1;
        memo[4] = 1;

        for (int i = 5; i <= m; i++) {
            int one = memo[i - 1];
            int three = memo[i - 3];
            int four = memo[i - 4];
            int min = Math.min(one, Math.min(three, four));
            memo[i] = min + 1;

//            System.out.println(i + " " + one + " " + three + " " + four + " " + min);
        }

        return memo[m];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

