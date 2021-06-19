package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class nx2_tiling2_11727 {
    public static void main(String[] args) throws IOException {
      correct();
    }

    private static void correct() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[1002];

        dp[1] = 1;
        int offset = 1;

        for (int i = 2; i <= N; i++)
        {
            dp[i] = (dp[i-1] * 2 + offset) % 10007;
            System.out.println(dp[i-1]);
            offset *= -1;
        }
        System.out.println(dp[N]);
    }
}
