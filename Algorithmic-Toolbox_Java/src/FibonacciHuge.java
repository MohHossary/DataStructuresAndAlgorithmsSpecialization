import java.util.Scanner;

public class FibonacciHuge {

    public static final double SQRT5 = Math.sqrt(5);

    private static double getFibonacciHuge(long n, long m) {
//        double phi = (1 + Math.sqrt(5)) / 2;
//        return (int) Math.round(Math.pow(phi, n) / Math.sqrt(5));
        return (1/ SQRT5)*(Math.pow((1 + SQRT5) / 2, n))-(Math.pow((1 + SQRT5) / 2, n)) % m;
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.format("%d, %d\n", i, fibonacci(i));
        }
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        long m = scanner.nextLong();
        System.out.println(getFibonacciHuge(n, m));
    }

    public static long fibonacci(long n) {
        return (long) ((Math.pow((1 + SQRT5) / 2, n))-(Math.pow((1 - SQRT5) / 2, n))/ SQRT5);
    }


}

