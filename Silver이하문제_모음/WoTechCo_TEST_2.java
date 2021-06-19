package Silver이하문제_모음;

import java.util.Arrays;

public class WoTechCo_TEST_2 {
    public static void main(String[] args) {

    }
    public static long[] solution(String s, String op) {
        long[] answer = new long[s.length()-1];
        int index = 0;

        for(int i = 1; i < s.length() - 1; i++)
        {
            String front = s.substring(0, i);
            String back = s.substring(i, s.length());

            int frontNum = Integer.parseInt(front);
            int backNum = Integer.parseInt(back);

            if (op.equals("+"))
                answer[index++] = frontNum + backNum;
            else if (op.equals("-"))
                answer[index++] = frontNum - backNum;
            else
                answer[index++] = frontNum * backNum;
        }

        Arrays.stream(answer).forEach(l -> System.out.print(l+" "));

        return answer;
    }
}
