import java.util.Scanner;

public class APlusB {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the numbers to be added: ");
        int n = sc.nextInt();
        int y = sc.nextInt();
        int sum = n + y;
        System.out.println("The sum of " + n + " and " + y + " is " + sum);
    }
}