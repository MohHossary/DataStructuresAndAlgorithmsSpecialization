import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DifferentSummands {
    private static List<Integer> optimalSummands(int n) {
        int remaining_candy = n;
        List<Integer> summands = new ArrayList<Integer>();
        int amount_to_take = 1;
        while (amount_to_take <= remaining_candy) {
            remaining_candy = remaining_candy - amount_to_take;
            summands.add(amount_to_take);
            amount_to_take++;
        }
        summands.set(summands.size() - 1, summands.get(summands.size()-1) + remaining_candy);
        return summands;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<Integer> summands = optimalSummands(n);
        System.out.println(summands.size());
        for (Integer summand : summands) {
            System.out.print(summand + " ");
        }
    }
}

