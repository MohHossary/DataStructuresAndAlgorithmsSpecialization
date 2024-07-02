import java.util.ArrayList;
import java.util.List;


class CelebrationParty {



    static int[] kidAges = {2, 4, 6, 10, 10, 10, 10, 10, 10, 12, 14, 15, 24, 36, 48, 100, 120, 240, 250};

    private static List<List<Integer>> groupKidsForParty(int[] kidAges) {
        int oldest = -1;
        int youngest;
        List<List<Integer>> groups = new ArrayList<>();
        List<Integer> group = new ArrayList<>();
        for (int kidAge: kidAges) {
            if (kidAge > oldest) {
                youngest = kidAge;
                oldest = youngest + 24;
                group = new ArrayList<>();
                groups.add(group);
            }
            group.add(kidAge);
        }
        return groups;
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int m = scanner.nextInt();
        System.out.println(groupKidsForParty(kidAges));

    }



}

