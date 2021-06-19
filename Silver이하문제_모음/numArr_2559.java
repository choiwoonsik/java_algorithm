package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class numArr_2559 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException
    {
        st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        int days = Integer.parseInt(st.nextToken());

        int[] dayArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int R;
        int L = 0;
        int sum = 0;
        int day = 0;
        int max = Integer.MIN_VALUE;
        for (R = 0; R < len; R++)
        {
            sum += dayArr[R];
            day++;
            if (day == days)
            {
                max = sum;
                R++;
                while (R < dayArr.length) {
                    sum += dayArr[R];
                    sum -= dayArr[L];
                    max = max > sum ? max : sum;
                    R++;
                    L++;
                }
            }
        }
        System.out.println(max);
    }
}
