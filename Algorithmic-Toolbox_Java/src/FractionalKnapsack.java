import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;



public class FractionalKnapsack {

    static class Item implements Comparable<Item>{
        int weight;
        int value;
        double valuePerUnit;

        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.valuePerUnit = ((double) value) / weight;
        }

        @Override
        public int compareTo(Item o) {
            return (int) Math.signum(this.valuePerUnit - o.valuePerUnit);
        }
    }

    private static double getOptimalValue(int remainingCapacity, int[] values, int[] weights) {
        Item[] items = new Item[values.length];
        double stolen = 0;
        Arrays.setAll(items, i -> new Item(weights[i], values[i]));
        Arrays.sort(items, Collections.reverseOrder());
        for (Item itemToSteal : items) {
            int weightOfNextGrab = Math.min(remainingCapacity, itemToSteal.weight);
            stolen += itemToSteal.valuePerUnit * weightOfNextGrab;
            remainingCapacity -= weightOfNextGrab;
            if (remainingCapacity == 0)
                break;
        }
        return stolen;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int capacity = scanner.nextInt();
        int[] values = new int[n];
        int[] weights = new int[n];
        for (int i = 0; i < n; i++) {
            values[i] = scanner.nextInt();
            weights[i] = scanner.nextInt();
        }
        System.out.printf("%.4f%n", getOptimalValue(capacity, values, weights));
    }
} 
