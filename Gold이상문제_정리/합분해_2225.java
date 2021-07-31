package Gold이상문제_정리;

/*
0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.
덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 합분해_2225 {
    static int N, K;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // K개를 더해서 N을 만들어야 한다. 이때 더하는 수는 중복 가능
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // K개를 더했을 때 N인 경우를 나타내는 DP
        // dp[K][N] = dp[K-1][N] + ... + dp[K-1][0]
        // N + 0 , N-1 + 1, N-2 + 2 ... 는 N이므로 쌍관계를 구할 수 있다.
        dp = new int[K+1][N+1];

        // 1개를 더했을 때 i인 경우의 수는 1개
        for (int i = 0; i <= N; i++) {
            dp[1][i] = 1;
        }

        for (int k = 1; k <= K; k++) {
            for (int n = 0; n <= N; n++) {
                for (int i = 0; i <= n; i++) {
                    dp[k][n] += dp[k - 1][n - i];
                    dp[k][n] %= 1_000_000_000;
                }
            }
        }
        System.out.println(dp[K][N]);
    }
}
