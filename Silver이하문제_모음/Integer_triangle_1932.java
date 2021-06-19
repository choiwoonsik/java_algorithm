package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Integer_triangle_1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] triangle = new int[501][501];
        int[][] dp = new int[501][501];

        for (int i  = 0; i < N; i++)
        {
            st = new StringTokenizer(br.readLine());
            int j = 0;
            while (st.hasMoreTokens())
                triangle[i][j++] = Integer.parseInt(st.nextToken());
        }
        int max = dp[0][0] = triangle[0][0];
        dp[1][0] = dp[0][0] + triangle[1][0];
        dp[1][1] = dp[0][0] + triangle[1][1];

        int len = 1;
        for (int i = 1; i < N; i++)
        {
            int j = 0;
            while (j <= len)
            {
                if (j == 0)
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                else if (j == len)
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                else
                    dp[i][j] = Math.max(dp[i - 1][j - 1] + triangle[i][j], dp[i - 1][j] + triangle[i][j]);
                max = Math.max(max, dp[i][j]);
                j++;
            }
            len++;
        }
        System.out.println(max);
    }
}
