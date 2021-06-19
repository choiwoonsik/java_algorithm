package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class calc_sum_11441 {
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int len = Integer.parseInt(st.nextToken());
        int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sumArr = new int[len];

        for (int i = 0; i < numArr.length; i++)
        {
            if (i == 0) sumArr[0] = numArr[0];
            else
            {
                sumArr[i] = sumArr[i - 1] + numArr[i];
            }
        }

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int big = sumArr[b];
            int small;
            if (a >= 1) {
                a -= 1;
                small = sumArr[a];
            }
            else
                small = 0;
            System.out.println(big - small);
        }
    }
}
