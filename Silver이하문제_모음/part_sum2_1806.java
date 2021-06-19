package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class part_sum2_1806 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] arr;
    public static void main(String[] args) throws IOException
    {
        int[] n = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = n[0];
        int target = n[1];
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int R = 0;
        int L = 0;
        int sum = 0;
        int len = 0;
        int min = Integer.MAX_VALUE;
        for (R = 0; R < arr.length; R++)
        {
            sum += arr[R];
            len++;
            while (L <= R && sum >= target)
            {
                if (min > len)
                    min = len;
                sum -= arr[L];
                len--;
                L++;
            }
        }
        if (min == Integer.MAX_VALUE)
            System.out.println(0);
        else
            System.out.println(min);
    }
}
