import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlacingParentheses {

    static long[][] mins;
    static long[][] maxs;

    static int[] nums;
    static char[] ops;

    public static long getMaximalValue(int[] nums, char[] ops) {
        if (nums.length == 1)
            return nums[0];

        mins = new long[nums.length][nums.length];
        maxs = new long[nums.length][nums.length];

        for (int i = 0; i < maxs.length; i++) {
            maxs[i][i] = mins[i][i] = nums[i];
        }

        int j;
        long[] minAndMax;
        int n = nums.length;
        for (int s = 1; s < n; s++) {
            for (int i = 0; i < n - s; i++) {
                j = i + s;
                minAndMax = get_min_and_max(i, j);
                mins[i][j] = minAndMax[0];
                maxs[i][j] = minAndMax[1];
            }
        }

        return maxs[0][maxs.length - 1];
    }

    public static long eval(long a, char op, long b) {
        return switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            default -> {
                assert false;
                yield 0;
            }
        };
    }

    private static long[] get_min_and_max(int i, int j) {
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long a, b, c, d;
        for (int k = i; k < j; k++) {
            a = eval(maxs[i][k], ops[k], maxs[k + 1][j]);
            b = eval(maxs[i][k], ops[k], mins[k + 1][j]);
            c = eval(mins[i][k], ops[k], maxs[k + 1][j]);
            d = eval(mins[i][k], ops[k], mins[k + 1][j]);
            min = Math.min(min, Math.min(Math.min(a, b), Math.min(c, d)));
            max = Math.max(max, Math.max(Math.max(a, b), Math.max(c, d)));
        }
        return new long[]{min, max};
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        Pattern pattern = Pattern.compile("([0-9]+)|(\\+)|(-)|(\\*)");
        Matcher matcher = pattern.matcher(line);
        ArrayList<Integer> numsArrayList = new ArrayList<>();
        ArrayList<String> opsArrayList = new ArrayList<>();
        while (matcher.find()) {
            String token = matcher.group(0);
            if (token.equals("+") || token.equals("-") || token.equals("*")) {
                opsArrayList.add(token);
            } else {
                numsArrayList.add(Integer.parseInt(token));
            }
        }
        int i = 0;
        nums = new int[numsArrayList.size()];
        ops = new char[opsArrayList.size()];

        for (i = 0; i < numsArrayList.size(); i++) {
            nums[i] = numsArrayList.get(i);
        }
        for (i = 0; i < opsArrayList.size(); i++) {
            ops[i] = opsArrayList.get(i).charAt(0);
        }
        System.out.println(getMaximalValue(nums, ops));
    }
}

