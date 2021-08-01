package Gold이상문제_정리;

import java.io.*;
import java.util.*;

public class 동전_9084 {
    static int T;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] coins = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());

            dp = new int[M + 1];
            dp[0] = 1;

            for (int coin : coins) {
                for (int m = coin; m <= M; m++) {
                    dp[m] += dp[m - coin];
                }
            }
            sb.append(dp[M]).append("\n");
        }
        System.out.print(sb);
    }
}
