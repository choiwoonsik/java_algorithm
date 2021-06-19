package Silver이하문제_모음;

import java.util.HashMap;

public class WoTechCo_TEST_1 {
    public static void main(String[] args) {
        //solution()
    }
    private static int solution(String[] grades, int[] weights, int threshold) {
        int answer = -1234567890;

        HashMap<String, Integer> table = new HashMap<>();
        table.put("A+", 10);
        table.put("A0", 9);
        table.put("B+", 8);
        table.put("B0", 7);
        table.put("C+", 6);
        table.put("C0", 5);
        table.put("D+", 4);
        table.put("D0", 3);
        table.put("F", 0);

        int sum = 0;
        for (int i = 0; i < grades.length; i++)
            sum += (table.get(grades[i]) * weights[i]);

        answer = sum - threshold;
        System.out.println(answer);
        return answer;
    }
}
