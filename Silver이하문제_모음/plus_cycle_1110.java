package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class plus_cycle_1110 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static int origin;
    static int count;
    public static void main(String[] args) throws IOException {
        int[] n = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        origin = n[0];
        ret_num(origin);
        System.out.println(count);
    }
    private static void ret_num(int num)
    {
        int tmp = 0;
        int multi_ten = 0;
        if (num == origin && count > 0)
            return ;
        count++;
        if (num < 10) {
            multi_ten = 10 * num;
            tmp = (multi_ten / 10 + multi_ten % 10) % 10;
            ret_num ((num % 10) * 10 + tmp);
        }
        else {
            tmp = (num / 10 + num % 10) % 10;
            ret_num ((num % 10) * 10 + tmp);
        }
    }
}
