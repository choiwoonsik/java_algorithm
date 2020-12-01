import java.util.LinkedList;
import java.util.Queue;

public class programmers_binary_change_loop {
    static int zeroCnt;
    public static void main(String[] args) {
        String s = "110010101001";
        int[] answer = solution(s);

        System.out.println(answer[0]+","+ answer[1]);
    }
    private static int[] solution(String s)
    {
        int[] answer;

        int loop_cnt = 0;
        int zero_sum = 0;
        while (s.equals("1"))
        {
            loop_cnt++;
            zeroCnt = 0;
            s = remove_zero(s);
            zero_sum += zeroCnt;

            s = change_to_binary(s);
        }
        System.out.println(loop_cnt +","+zero_sum);
        answer = new int[]{loop_cnt, zero_sum};
        return answer; 
    }

    private static String change_to_binary(String s) {
        int len = s.length();
        s = Integer.toBinaryString(len);
        return s;
    }

    private static String remove_zero(String s) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            if (s.charAt(i) == '1')
                str.append("1");
        }
        //0의 개수 : 원래 길이 - 1로 만 이뤄진 길이
        zeroCnt = s.length() - str.length();
        s = str.toString();
        return s;
    }
}
