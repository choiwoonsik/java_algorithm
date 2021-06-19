package Silver이하문제_모음;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PadoClass_sequence_9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        dp[5] = 2;

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < N; i++)
        {
            int K = Integer.parseInt(br.readLine());
            for (int j = 6; j <= K; j++)
            {
                dp[j] = dp[j-1] + dp[j-5];
            }
            str.append(dp[K]).append("\n");
        }
        System.out.print(str);
    }
}
