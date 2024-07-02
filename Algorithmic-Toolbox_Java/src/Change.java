import java.util.Scanner;

public class Change {

    static int[] denums = new int[] {10, 5, 1};

    private static int getChange(int m) {
        int numOfCoins = 0;
        for (int denum: denums) {
            // modify numOfCoins and m
            numOfCoins += m / denum;
            m = m % denum;
            if (m == 0) break;
        }
        return numOfCoins;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        System.out.println(getChange(m));

    }
}

