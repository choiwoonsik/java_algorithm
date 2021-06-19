package Silver이하문제_모음;

import java.util.*;

public class WoTechCo_TEST_6 {
    public static void main(String[] args) {

    }
    private static String[] solution(String[] logs) {
        String[] answer = {};
        HashMap<Integer, List<Test_problem>> table = new HashMap<>();

        StringTokenizer st;
        for (int i = 0; i < logs.length; i++)
        {
            st = new StringTokenizer(logs[i], " ");
            int who = Integer.parseInt(st.nextToken());
            int test_n = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());
            table.put(who, new ArrayList<>());
            table.get(who).add(new Test_problem(test_n, point));
        }

        return answer;
    }
}

class Test_problem implements Comparator<Test_problem>
{
    int testN;
    int point;
    public Test_problem(int testN, int point) {
        this.testN = testN;
        this.point = point;
    }

    @Override
    public int compare(Test_problem o1, Test_problem o2) {
        if (o1.point == o2.point) {
            if (o1.testN == o2.testN) {
                return 0;
            }
        }
        return -1;
    }
}
