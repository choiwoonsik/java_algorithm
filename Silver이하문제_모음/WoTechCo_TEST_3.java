package Silver이하문제_모음;

public class WoTechCo_TEST_3 {
    public static void main(String[] args) {
        //solution()
    }
    private static int solution(int money, String[] expected, String[] actual) {
        int startBetting = 100;
        for (int i = 0; i < expected.length; i++)
        {
            if (expected[i].equals(actual[i]))
            {
                money += startBetting;
                startBetting = 100;
                System.out.println(money);
            }
            else {
                money -= startBetting;
                System.out.println(money);
                startBetting *= 2;
                if (money < startBetting)
                    startBetting = money;
                if (money == 0)
                    break;
            }
        }
        System.out.println(" > "+money);

        return money;
    }
}
